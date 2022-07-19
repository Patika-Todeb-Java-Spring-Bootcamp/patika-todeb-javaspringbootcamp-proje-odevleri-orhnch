package com.bootcamp.StudentManagementSystem.model.dto;

import com.bootcamp.StudentManagementSystem.model.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassDTO {
    private String name;
    private Department department;
}
