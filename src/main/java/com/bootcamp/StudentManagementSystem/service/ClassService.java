package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.exception.EntityNotFoundException;
import com.bootcamp.StudentManagementSystem.model.dto.ClassDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Class;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
import com.bootcamp.StudentManagementSystem.model.mapper.ClassMapper;
import com.bootcamp.StudentManagementSystem.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;

    private final DepartmentService departmentService;

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
        return byId.orElseThrow(() ->{
            log.error("Class could not found by id: " +id);
            return new EntityNotFoundException("Class", "id :" + id);
        });
    }

    public void delete(Long id) {
        getClassById(id);
        classRepository.deleteById(id);
    }

    public Class update(Long id, ClassDTO class1) {
        Class updatedClass = getClassById(id);
        if (class1.getLevel() > 0) {
            updatedClass.setLevel(class1.getLevel());
        }
        return classRepository.save(updatedClass);
    }

    public void deleteAll() {
        classRepository.deleteAll();
    }

    public Class addDepartmentToClass(Long id, Department department) {
        Class class1 = getClassById(id);
        Department departmentById = departmentService.getDepartmentById(department.getId());
        class1.setDepartment(departmentById);
        return classRepository.save(class1);
    }
}
