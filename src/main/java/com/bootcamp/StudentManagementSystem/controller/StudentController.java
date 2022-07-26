package com.bootcamp.StudentManagementSystem.controller;

import com.bootcamp.StudentManagementSystem.model.dto.StudentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Class;
import com.bootcamp.StudentManagementSystem.model.entity.Course;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
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
    public ResponseEntity getAllStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return ResponseEntity.ok(allStudents);
    }

    @GetMapping("/{id}")
    public ResponseEntity getStudentByID(@PathVariable Long id) {
        Student studentById = studentService.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentById);
    }

    @PostMapping("/create")
    public ResponseEntity createNewStudent(@RequestBody StudentDTO student) {
        Student respStudent = studentService.create(student);
        if (respStudent == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Student could not be created successfully");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(respStudent);
    }

    @DeleteMapping
    public ResponseEntity deleteStudent(@RequestParam(name = "id") Long id) {
        try {
            studentService.delete(id);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Related Student deleted successfully");
    }

    @PutMapping("/{email}")
    public ResponseEntity updateStudent(
            @PathVariable String email,
            @RequestBody StudentDTO student) {
        Student update = studentService.update(email, student);
        if (update == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Prelector could not be updated successfully");
        }
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/all")
    public ResponseEntity deleteAllStudents() {
        try {
            studentService.deleteAll();
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("All students were deleted successfully");
    }

    @GetMapping("get/{firstName}")
    public ResponseEntity getAllByFirstNameContainingIgnoreCase(@PathVariable String firstName) {
        List<Student> allStudents = studentService.getAllByFirstNameContainingIgnoreCase(firstName);
        return ResponseEntity.ok(allStudents);
    }

    @PutMapping("add/class/{id}")
    public ResponseEntity addClassToStudent(
            @PathVariable Long id,
            @RequestBody Class class1) {
        Student addedClass = studentService.addClassToStudent(id, class1);
        if (addedClass == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Related Class could not be added to related Student successfully");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Related Class was added to related Student successfully");
    }

    @PutMapping("add/department/{id}")
    public ResponseEntity addDepartmentToStudent(
            @PathVariable Long id,
            @RequestBody Department department) {
        Student addedDepartment = studentService.addDepartmentToStudent(id, department);
        if (addedDepartment == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Related Department could not be added to related Student successfully");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Related Department was added to related Student successfully");
    }

    @PutMapping("add/course/{id}")
    public ResponseEntity addCourseToStudent(
            @PathVariable Long id,
            @RequestBody Course course) {
        Student addedCourse = studentService.addCourseToStudent(id, course);
        if (addedCourse == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Related Course could not be added to related Student successfully");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Related Course was added to related Student successfully");
    }

}
