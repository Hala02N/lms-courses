package com.example.lmscourses.controllers;

import com.example.lmscourses.entities.CourseEntity;
import com.example.lmscourses.requests.CreateCourseRequest;
import com.example.lmscourses.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody @Valid CreateCourseRequest request){
        CourseEntity createdCourse = courseService.saveCourse(request);
        return ResponseEntity.ok(createdCourse);
    }
}
