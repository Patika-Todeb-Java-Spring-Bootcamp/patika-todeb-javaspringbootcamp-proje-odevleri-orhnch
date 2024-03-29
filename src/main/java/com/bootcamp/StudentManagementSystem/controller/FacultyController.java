package com.bootcamp.StudentManagementSystem.controller;


import com.bootcamp.StudentManagementSystem.model.dto.FacultyDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Faculty;
import com.bootcamp.StudentManagementSystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/all")
    public ResponseEntity getAllFaculties() {
        List<Faculty> allFaculties = facultyService.getAllFaculties();
        return ResponseEntity.ok(allFaculties);
    }

    @GetMapping("/{id}")
    public ResponseEntity getFacultyByID(@PathVariable Long id) {
        Faculty facultyById = facultyService.getFacultyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(facultyById);
    }

    @GetMapping("get/faculty/{name}")
    public ResponseEntity getFacultyByName(@PathVariable String name) {
        Faculty facultyByName = facultyService.getFacultyByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(facultyByName);
    }


    @PostMapping("/create")
    public ResponseEntity createNewFaculty(@RequestBody FacultyDTO faculty) {
        Faculty respFaculty = facultyService.create(faculty);
        if (respFaculty == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Faculty could not be created successfully");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(respFaculty);
    }

    @DeleteMapping
    public ResponseEntity deleteFaculty(@RequestParam(name = "id") Long id) {
        try {
            facultyService.delete(id);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Related Faculty deleted successfully");
    }

    @PutMapping("/{name}")
    public ResponseEntity updateFaculty(
            @PathVariable String name,
            @RequestBody FacultyDTO faculty) {
        Faculty update = facultyService.update(name, faculty);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/all")
    public ResponseEntity deleteAllFaculties() {
        facultyService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All faculties were deleted successfully");
    }

    @GetMapping("get/{name}")
    public ResponseEntity getAllByNameContainingIgnoreCase(@PathVariable String name) {
        List<Faculty> allFaculties = facultyService.getAllByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(allFaculties);
    }
}
