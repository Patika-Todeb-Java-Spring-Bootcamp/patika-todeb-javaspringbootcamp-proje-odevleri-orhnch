package com.bootcamp.StudentManagementSystem.model.dto;

import com.bootcamp.StudentManagementSystem.model.entity.Faculty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDTO {
    private String name;
    private Faculty faculty;
}
