package com.bootcamp.StudentManagementSystem.controller;

import com.bootcamp.StudentManagementSystem.model.dto.StudentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Student;
import com.bootcamp.StudentManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity getAllStudents(){
        List<Student> allStudents = studentService.getAllStudents();
        return ResponseEntity.ok(allStudents);
    }

    @PostMapping("/create")
    public ResponseEntity createNewStudent(@RequestBody StudentDTO student){
        Student respStudent = studentService.create(student);
        if(respStudent==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Student could not be created successfully");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(respStudent);
    }
}
