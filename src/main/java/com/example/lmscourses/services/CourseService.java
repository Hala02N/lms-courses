package com.example.lmscourses.services;

import com.example.lmscourses.entities.CourseEntity;
import com.example.lmscourses.requests.CreateCourseRequest;

public interface CourseService {
    void createStudentCourseRecord(Integer userId, String major);
    CourseEntity saveCourse(CreateCourseRequest request);

    void deleteAllCoursesOfUser(Integer userId);
}
