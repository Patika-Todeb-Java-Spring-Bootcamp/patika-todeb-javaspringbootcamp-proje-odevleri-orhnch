package com.bootcamp.StudentManagementSystem.model.mapper;

import com.bootcamp.StudentManagementSystem.model.dto.DepartmentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Department;

public class DepartmentMapper {
    public static DepartmentDTO toDTO(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName(department.getName());
        departmentDTO.setFaculty(department.getFaculty());
        return departmentDTO;
    }
    public static Department toEntity(DepartmentDTO departmentDTO){
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setFaculty(departmentDTO.getFaculty());
        return department;
    }
}
