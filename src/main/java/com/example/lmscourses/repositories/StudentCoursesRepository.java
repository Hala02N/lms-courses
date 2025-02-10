package com.example.lmscourses.repositories;

import com.example.lmscourses.entities.StudentCoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface StudentCoursesRepository extends JpaRepository<StudentCoursesEntity, Integer> {
    ArrayList<StudentCoursesEntity> findAllById(Integer id);
    @Transactional
    @Modifying
    void deleteByStudentId(Integer studentId);
}
