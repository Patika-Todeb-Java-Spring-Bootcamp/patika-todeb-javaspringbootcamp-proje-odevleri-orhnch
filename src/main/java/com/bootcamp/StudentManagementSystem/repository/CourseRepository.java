package com.bootcamp.StudentManagementSystem.repository;

import com.bootcamp.StudentManagementSystem.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Override
    Optional<Course> findById(Long id);

    @Override
    List<Course> findAll();
}
