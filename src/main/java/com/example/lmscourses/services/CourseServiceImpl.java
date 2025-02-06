package com.example.lmscourses.services;

import com.example.lmscourses.entities.CourseEntity;
import com.example.lmscourses.entities.StudentCoursesEntity;
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
        // it can be optimized by saveAll
        for(CourseEntity course: courses) {
            StudentCoursesEntity studentCoursesEntity = new StudentCoursesEntity();
            studentCoursesEntity.setCourseId(course.getId());
            studentCoursesEntity.setStudentId(userId);
            studentCoursesRepository.save(studentCoursesEntity);  // can be enhanced to hit the db once
        }
        System.out.println("Courses registered successfully to the student of id: " + userId);
    }
    @Override
    public CourseEntity saveCourse(CreateCourseRequest request){
        CourseEntity courseFromCode = courseRepository.findByCode(request.getCode());
        if(courseFromCode != null)
            throw new RuntimeException("Course already exists");

        Object courseCode = redisTemplate.opsForValue().get("Code");
        if(courseCode != null){
            throw new RuntimeException("Can't create course due to duration restrictions");
        }
        CourseEntity course = new CourseEntity();
        course.setName(request.getName());
        course.setMajor(request.getMajor());

        course.setCode(request.getCode());
        // Get course from redis if any exists

        // Rate Limiting using redis
        redisTemplate.opsForValue().set("Code", request.getCode());
        redisTemplate.expire("Code", 24, TimeUnit.HOURS);
        return courseRepository.save(course);
    }

}
