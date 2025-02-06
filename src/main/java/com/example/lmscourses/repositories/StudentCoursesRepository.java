package com.example.lmscourses.repositories;

import com.example.lmscourses.entities.StudentCoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCoursesRepository extends JpaRepository<StudentCoursesEntity, Integer> {
}
