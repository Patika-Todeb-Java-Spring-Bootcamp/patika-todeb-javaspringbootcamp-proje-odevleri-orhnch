package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.model.dto.StudentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Student;
import com.bootcamp.StudentManagementSystem.model.mapper.StudentMapper;
import com.bootcamp.StudentManagementSystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        return allStudents;
    }

    public Student create(StudentDTO studentDTO){
        Student student = StudentMapper.toEntity(studentDTO);
        return studentRepository.save(student);
    }
}
