package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.model.dto.ClassDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Class;
import com.bootcamp.StudentManagementSystem.model.mapper.ClassMapper;
import com.bootcamp.StudentManagementSystem.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;

    public List<Class> getAllClasses() {
        List<Class> allClasses = classRepository.findAll();
        return allClasses;
    }

    public Class create(ClassDTO classDTO) {
        Class class1 = ClassMapper.toEntity(classDTO);
        return classRepository.save(class1);
    }

    public Class getClassById(Long id) {
        Optional<Class> byId = classRepository.findById(id);
        return byId.orElseThrow(() -> new RuntimeException("Class not found!"));
    }
}
