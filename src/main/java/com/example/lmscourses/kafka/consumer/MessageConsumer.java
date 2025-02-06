package com.example.lmscourses.kafka.consumer;

import com.example.lmscourses.events.CreateUserEvent;
import com.example.lmscourses.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @Autowired
    CourseService courseService;

    @KafkaListener(topics = "lms-topic2", groupId = "lms-consumer-group")
    public void listen(CreateUserEvent userEvent) {
        System.out.println("Received message: " + userEvent.toString());

        // create new records in the student_courses table
       courseService.createStudentCourseRecord(userEvent.getId(), userEvent.getMajor());
    }
}
