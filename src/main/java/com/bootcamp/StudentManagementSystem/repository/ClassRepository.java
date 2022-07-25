package com.bootcamp.StudentManagementSystem.repository;

import com.bootcamp.StudentManagementSystem.model.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    Optional<Class> findClassByLevel(Integer level);
}
