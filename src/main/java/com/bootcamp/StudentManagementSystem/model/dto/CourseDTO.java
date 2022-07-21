package com.bootcamp.StudentManagementSystem.model.dto;

import com.bootcamp.StudentManagementSystem.model.entity.Class;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private String title;
    private Integer quota;
    private String code;
    private Class courseClass;
    private Prelector prelector;
    private String letterGrade;
    private Integer grade;
}
