package com.example.lmscourses.repositories;

import com.example.lmscourses.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    public ArrayList<CourseEntity> findByMajor(String major);

    CourseEntity findByCode(String code);
}
