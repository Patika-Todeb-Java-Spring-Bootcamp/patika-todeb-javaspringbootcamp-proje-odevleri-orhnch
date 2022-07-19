package com.bootcamp.StudentManagementSystem.controller;

import com.bootcamp.StudentManagementSystem.model.dto.CourseDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Course;
import com.bootcamp.StudentManagementSystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity getAllCourses() {
        List<Course> allCourses = courseService.getAllCourses();
        return ResponseEntity.ok(allCourses);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable Long id) {
        Course courseById;
        try {
            courseById = courseService.getCourseById(id);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseById);
    }

    @PostMapping("/create")
    public ResponseEntity createNewCourse(@RequestBody CourseDTO course) {
        Course respCourse = courseService.create(course);
        if (respCourse == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Course could not be created successfully");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(respCourse);
    }
}
