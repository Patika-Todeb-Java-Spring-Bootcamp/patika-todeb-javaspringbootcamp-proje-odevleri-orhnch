package com.bootcamp.StudentManagementSystem.repository;

import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrelectorRepository extends JpaRepository<Prelector, Long> {
    Optional<Prelector> findPrelectorByEmail(String email);

}
