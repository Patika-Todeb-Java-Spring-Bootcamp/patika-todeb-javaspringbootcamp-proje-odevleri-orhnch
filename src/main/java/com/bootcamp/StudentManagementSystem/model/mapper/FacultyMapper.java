package com.bootcamp.StudentManagementSystem.model.mapper;

import com.bootcamp.StudentManagementSystem.model.dto.FacultyDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Faculty;

public class FacultyMapper {
    public static FacultyDTO toDTO(Faculty faculty){
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setName(faculty.getName());
        return facultyDTO;
    }
    public static Faculty toEntity(FacultyDTO facultyDTO){
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getName());
        return faculty;
    }
}
