package com.example.lmscourses.services;

import com.example.lmscourses.entities.CourseEntity;
import com.example.lmscourses.entities.StudentCoursesEntity;
import com.example.lmscourses.exceptions.CourseCreationException;
import com.example.lmscourses.repositories.CourseRepository;
import com.example.lmscourses.repositories.StudentCoursesRepository;
import com.example.lmscourses.requests.CreateCourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentCoursesRepository studentCoursesRepository;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void createStudentCourseRecord(Integer userId, String major) {
        ArrayList<CourseEntity> courses = courseRepository.findByMajor(major);
        ArrayList<StudentCoursesEntity> studentCoursesEntities = new ArrayList<>();
        // it can be optimized by saveAll
        for(CourseEntity course: courses) {
            StudentCoursesEntity studentCoursesEntity = new StudentCoursesEntity();
            studentCoursesEntity.setCourseId(course.getId());
            studentCoursesEntity.setStudentId(userId);
            studentCoursesEntities.add(studentCoursesEntity);
        }
        studentCoursesRepository.saveAll(studentCoursesEntities);  // Enhanced to hit the db once
        System.out.println("Courses registered successfully to the student of id: " + userId);
    }
    @Override
    public CourseEntity saveCourse(CreateCourseRequest request) throws CourseCreationException {
        // Get course from redis if any exists
        Object courseCode = redisTemplate.opsForValue().get("Code");
        if(courseCode != null){
            throw new CourseCreationException("Can't create course due to duration restrictions");
        }

        // Check if course already exist
        CourseEntity courseFromCode = courseRepository.findByCode(request.getCode());
        if(courseFromCode != null)
            throw new CourseCreationException("Course already exists");

        // If new course, create entity and populate it
        CourseEntity course = new CourseEntity();
        course.setName(request.getName());
        course.setMajor(request.getMajor());
        course.setCode(request.getCode());

        // Store course record in Redis DB & apply Rate Limiting
        redisTemplate.opsForValue().set("Code", request.getCode());
        redisTemplate.expire("Code", 24, TimeUnit.HOURS);
        return courseRepository.save(course);
    }

}
