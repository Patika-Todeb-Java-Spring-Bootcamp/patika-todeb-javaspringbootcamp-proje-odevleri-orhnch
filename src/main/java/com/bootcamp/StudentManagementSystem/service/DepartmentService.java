package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.model.dto.DepartmentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
import com.bootcamp.StudentManagementSystem.model.mapper.DepartmentMapper;
import com.bootcamp.StudentManagementSystem.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        List<Department> allDepartments = departmentRepository.findAll();
        return allDepartments;
    }

    public Department getDepartmentById(Long id) {
        Optional<Department> byId = departmentRepository.findById(id);
        return byId.orElseThrow(() -> new RuntimeException("Department not found!"));
    }

    public Department create(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.toEntity(departmentDTO);
        return departmentRepository.save(department);
    }
    public void delete(Long id) {
        getDepartmentById(id);
        departmentRepository.deleteById(id);
    }
}
