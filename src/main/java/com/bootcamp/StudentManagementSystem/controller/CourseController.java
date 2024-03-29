package com.bootcamp.StudentManagementSystem.controller;

import com.bootcamp.StudentManagementSystem.model.dto.CourseDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Class;
import com.bootcamp.StudentManagementSystem.model.entity.Course;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
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
        Course courseById = courseService.getCourseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(courseById);
    }
    @GetMapping("get/course/{code}")
    public ResponseEntity getCourseByCode(@PathVariable String code) {
        Course courseByCode = courseService.getCourseByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(courseByCode);
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

    @DeleteMapping
    public ResponseEntity deleteCourse(@RequestParam(name = "id") Long id) {
        try {
            courseService.delete(id);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Related Course deleted successfully");
    }

    @PutMapping("/{code}")
    public ResponseEntity updateCourse(
            @PathVariable String code,
            @RequestBody CourseDTO course) {
        Course update = courseService.update(code, course);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/all")
    public ResponseEntity deleteAllCourses() {
        courseService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All courses were deleted successfully");
    }

    @GetMapping("get/{title}")
    public ResponseEntity getAllByTitleContainingIgnoreCase(@PathVariable String title) {
        List<Course> allCourses = courseService.getAllByTitleContainingIgnoreCase(title);
        return ResponseEntity.ok(allCourses);
    }

    @PutMapping("add/class/{id}")
    public ResponseEntity addClassToCourse(
            @PathVariable Long id,
            @RequestBody Class class1) {
        courseService.addClassToCourse(id, class1);
        return ResponseEntity.status(HttpStatus.OK).body("Related Class was added to related Course successfully");
    }

    @PutMapping("add/prelector/{id}")
    public ResponseEntity addPrelectorToCourse(
            @PathVariable Long id,
            @RequestBody Prelector prelector) {
        courseService.addPrelectorToCourse(id, prelector);

        return ResponseEntity.status(HttpStatus.OK).body("Related Prelector was added to related Course successfully");
    }
}
