package com.bootcamp.StudentManagementSystem.repository;

import com.bootcamp.StudentManagementSystem.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}