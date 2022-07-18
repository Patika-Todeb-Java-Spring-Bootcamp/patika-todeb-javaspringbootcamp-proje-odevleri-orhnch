package com.bootcamp.StudentManagementSystem.model.dto;

import com.bootcamp.StudentManagementSystem.model.entity.Class;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private String title;
    private int quota;
    private String code;
    private int courseClass;
}
