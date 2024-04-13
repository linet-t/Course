package com.example.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Method to filter courses by OU
    List<Course> findByOu(String ou);

    // Method to filter courses by status
    List<Course> findByStatus(String status);
}