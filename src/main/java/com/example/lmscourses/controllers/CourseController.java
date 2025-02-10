package com.example.lmscourses.controllers;

import com.example.lmscourses.entities.CourseEntity;
import com.example.lmscourses.exceptions.RequestValidationException;
import com.example.lmscourses.requests.CreateCourseRequest;
import com.example.lmscourses.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody @Valid CreateCourseRequest request){
        try{
            CourseEntity createdCourse = courseService.saveCourse(request);
            return ResponseEntity.ok(createdCourse);
        }catch(RequestValidationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteCoursesFromUserId(@PathVariable("id") Integer userId){
        courseService.deleteAllCoursesOfUser(userId);
        return ResponseEntity.ok("Courses of User with ID " + userId
        + " has been deleted successfully");
    }
}
