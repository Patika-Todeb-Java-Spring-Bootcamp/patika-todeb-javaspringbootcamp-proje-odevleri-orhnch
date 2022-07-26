package com.bootcamp.StudentManagementSystem.controller;

import com.bootcamp.StudentManagementSystem.model.dto.DepartmentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
import com.bootcamp.StudentManagementSystem.model.entity.Faculty;
import com.bootcamp.StudentManagementSystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity getAllDepartments() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        return ResponseEntity.ok(allDepartments);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDepartmentById(@PathVariable Long id) {
        Department departmentById = departmentService.getDepartmentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(departmentById);
    }

    @PostMapping("/create")
    public ResponseEntity createNewDepartment(@RequestBody DepartmentDTO department) {
        Department respDepartment = departmentService.create(department);
        if (respDepartment == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Department could not be created successfully");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(respDepartment);
    }

    @DeleteMapping
    public ResponseEntity deleteDepartment(@RequestParam(name = "id") Long id) {
        try {
            departmentService.delete(id);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Related Department deleted successfully");
    }

    @PutMapping("/{name}")
    public ResponseEntity updateDepartment(
            @PathVariable String name,
            @RequestBody DepartmentDTO department) {
        Department update = departmentService.update(name, department);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/all")
    public ResponseEntity deleteAllDepartments() {
        try {
            departmentService.deleteAll();
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("All departments were deleted successfully");
    }

    @GetMapping("get/{name}")
    public ResponseEntity getAllByNameContainingIgnoreCase(@PathVariable String name) {
        List<Department> allDepartments = departmentService.getAllByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(allDepartments);
    }

    @PutMapping("add/faculty/{id}")
    public ResponseEntity addFacultyToDepartment(
            @PathVariable Long id,
            @RequestBody Faculty faculty) {
        Department addedFaculty = departmentService.addFacultyToDepartment(id, faculty);
        if (addedFaculty == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Related Faculty could not be added to related Department successfully");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Related Faculty was added to related Department successfully");
    }
}
