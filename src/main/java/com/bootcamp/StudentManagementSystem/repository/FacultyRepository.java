package com.bootcamp.StudentManagementSystem.repository;

import com.bootcamp.StudentManagementSystem.model.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
