package com.bootcamp.StudentManagementSystem.controller;

import com.bootcamp.StudentManagementSystem.model.dto.ClassDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Class;
import com.bootcamp.StudentManagementSystem.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/all")
    public ResponseEntity getAllClasses(){
        List<Class> allClasses = classService.getAllClasses();
        return ResponseEntity.ok(allClasses);
    }

    @PostMapping("/create")
    public ResponseEntity createNewClass(@RequestBody ClassDTO class1){
        Class respClass = classService.create(class1);
        if(respClass==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Class could not be created successfully");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(respClass);
    }

}
