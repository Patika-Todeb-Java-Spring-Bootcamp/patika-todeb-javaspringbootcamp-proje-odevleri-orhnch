package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.exception.EntityNotFoundException;
import com.bootcamp.StudentManagementSystem.model.dto.FacultyDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Faculty;
import com.bootcamp.StudentManagementSystem.model.mapper.FacultyMapper;
import com.bootcamp.StudentManagementSystem.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculties() {
        List<Faculty> allFaculties = facultyRepository.findAll();
        return allFaculties;
    }

    public Faculty getFacultyById(Long id) {
        Optional<Faculty> byId = facultyRepository.findById(id);
        return byId.orElseThrow(() -> new EntityNotFoundException("Faculty","id: "+ id));
    }

    public Faculty create(FacultyDTO facultyDTO) {
        Faculty faculty = FacultyMapper.toEntity(facultyDTO);
        return facultyRepository.save(faculty);
    }

    public void delete(Long id) {
        getFacultyById(id);
        facultyRepository.deleteById(id);
    }

    public Faculty update(String name, FacultyDTO faculty) {
        Optional<Faculty> facultyByName = facultyRepository.findFacultyByName(name);
        if (!facultyByName.isPresent())
            throw new EntityNotFoundException("Faculty","name: "+ name);
        Faculty updatedFaculty = facultyByName.get();
        if (!StringUtils.isEmpty(faculty.getName())) {
            updatedFaculty.setName(faculty.getName());
        }

        return facultyRepository.save(updatedFaculty);
    }

    public void deleteAll() {
        facultyRepository.deleteAll();
    }

    public List<Faculty> getAllByNameContainingIgnoreCase(String name) {
        List<Faculty> allFaculties = facultyRepository.getAllByNameContainingIgnoreCase(name);
        return allFaculties;
    }
}
