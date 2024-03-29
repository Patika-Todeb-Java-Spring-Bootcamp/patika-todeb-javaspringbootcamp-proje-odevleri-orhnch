package com.bootcamp.StudentManagementSystem.controller;

import com.bootcamp.StudentManagementSystem.model.dto.ClassDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Class;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
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
    public ResponseEntity getAllClasses() {
        List<Class> allClasses = classService.getAllClasses();
        return ResponseEntity.ok(allClasses);
    }

    @PostMapping("/create")
    public ResponseEntity createNewClass(@RequestBody ClassDTO class1) {
        Class respClass = classService.create(class1);
        if (respClass == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Class could not be created successfully");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(respClass);
    }

    @GetMapping("/{id}")
    public ResponseEntity getClassById(@PathVariable Long id) {
        Class classById = classService.getClassById(id);
        return ResponseEntity.status(HttpStatus.OK).body(classById);
    }

    //localhost:8080/api/class?id=value
    @DeleteMapping
    public ResponseEntity deleteClass(@RequestParam(name = "id") Long id) {
        try {
            classService.delete(id);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Related Class deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClass(
            @PathVariable Long id,
            @RequestBody ClassDTO class1) {
        Class update = classService.update(id, class1);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/all")
    public ResponseEntity deleteAllClasses() {
        classService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All classes were deleted successfully");
    }

    @PutMapping("add/department/{id}")
    public ResponseEntity addDepartmentToClass(
            @PathVariable Long id,
            @RequestBody Department department) {
        classService.addDepartmentToClass(id, department);
        return ResponseEntity.status(HttpStatus.OK).body("Related Department was added to related Class successfully");
    }

}
