package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.model.dto.CourseDTO;
import com.bootcamp.StudentManagementSystem.model.dto.FacultyDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Course;
import com.bootcamp.StudentManagementSystem.model.entity.Faculty;
import com.bootcamp.StudentManagementSystem.model.mapper.CourseMapper;
import com.bootcamp.StudentManagementSystem.model.mapper.FacultyMapper;
import com.bootcamp.StudentManagementSystem.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculties() {
        List<Faculty> allFaculties = facultyRepository.findAll();
        return allFaculties;
    }
    public Faculty create(FacultyDTO facultyDTO){
        Faculty faculty = FacultyMapper.toEntity(facultyDTO);
        return facultyRepository.save(faculty);
    }
}
