package com.bootcamp.StudentManagementSystem.controller;

import com.bootcamp.StudentManagementSystem.model.dto.PrelectorDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import com.bootcamp.StudentManagementSystem.service.PrelectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prelector")
public class PrelectorController {

    @Autowired
    private PrelectorService prelectorService;

    @GetMapping("/all")
    public ResponseEntity getAllPrelectors() {
        List<Prelector> allPrelectors = prelectorService.getAllPrelectors();
        return ResponseEntity.ok(allPrelectors);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPrelectorByID(@PathVariable Long id) {
        Prelector prelectorById = prelectorService.getPrelectorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(prelectorById);
    }

    @GetMapping("get/prelector/{email}")
    public ResponseEntity getPrelectorByEmail(@PathVariable String email) {
        Prelector prelectorByEmail = prelectorService.getPrelectorByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(prelectorByEmail);
    }

    @PostMapping("/create")
    public ResponseEntity createNewPrelectors(@RequestBody PrelectorDTO prelector) {
        Prelector respPrelector = prelectorService.create(prelector);
        if (respPrelector == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Department could not be created successfully");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(respPrelector);
    }

    @DeleteMapping
    public ResponseEntity deleteCourse(@RequestParam(name = "id") Long id) {
        try {
            prelectorService.delete(id);
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Related Prelector deleted successfully");
    }

    @PutMapping("/{email}")
    public ResponseEntity updatePrelector(
            @PathVariable String email,
            @RequestBody PrelectorDTO prelector) {
        Prelector update = prelectorService.update(email, prelector);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/all")
    public ResponseEntity deleteAllCourses() {
        prelectorService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All prelectors were deleted successfully");
    }

    @GetMapping("get/{firstName}")
    public ResponseEntity getAllByFirstNameContainingIgnoreCase(@PathVariable String firstName) {
        List<Prelector> allPrelectors = prelectorService.getAllByFirstNameContainingIgnoreCase(firstName);
        return ResponseEntity.ok(allPrelectors);
    }

    @PutMapping("add/department/{id}")
    public ResponseEntity addDepartmentToPrelector(
            @PathVariable Long id,
            @RequestBody Department department) {
        prelectorService.addDepartmentToPrelector(id, department);
        return ResponseEntity.status(HttpStatus.OK).body("Related Department was added to related Prelector successfully");
    }
}
