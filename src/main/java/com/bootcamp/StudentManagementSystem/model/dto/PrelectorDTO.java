package com.bootcamp.StudentManagementSystem.model.dto;

import com.bootcamp.StudentManagementSystem.model.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrelectorDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Department department;
}
