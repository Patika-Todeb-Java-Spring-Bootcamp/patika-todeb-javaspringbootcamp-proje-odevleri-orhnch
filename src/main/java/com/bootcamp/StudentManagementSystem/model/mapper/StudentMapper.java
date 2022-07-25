package com.bootcamp.StudentManagementSystem.model.mapper;

import com.bootcamp.StudentManagementSystem.model.dto.StudentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Student;

public class StudentMapper {
    public static StudentDTO toDTO (Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;
    }

    public static Student toEntity(StudentDTO studentDTO){
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        return student;
    }
}
