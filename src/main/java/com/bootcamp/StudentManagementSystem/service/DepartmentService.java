package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.exception.EntityNotFoundException;
import com.bootcamp.StudentManagementSystem.model.dto.DepartmentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
import com.bootcamp.StudentManagementSystem.model.entity.Faculty;
import com.bootcamp.StudentManagementSystem.model.mapper.DepartmentMapper;
import com.bootcamp.StudentManagementSystem.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final FacultyService facultyService;

    public List<Department> getAllDepartments() {
        List<Department> allDepartments = departmentRepository.findAll();
        return allDepartments;
    }

    public Department getDepartmentById(Long id) {
        Optional<Department> byId = departmentRepository.findById(id);
        return byId.orElseThrow(() -> new EntityNotFoundException("Department", "id: " + id));
    }
    public Department getDepartmentByName(String name) {
        Optional<Department> departmentByName = departmentRepository.findDepartmentByName(name);
        return departmentByName.orElseThrow(() -> new EntityNotFoundException("Department", "name: " + name));
    }

    public Department create(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.toEntity(departmentDTO);
        return departmentRepository.save(department);
    }

    public void delete(Long id) {
        getDepartmentById(id);
        departmentRepository.deleteById(id);
    }

    public Department update(String name, DepartmentDTO department) {
        Department updatedDepartment = getDepartmentByName(name);
        if (!StringUtils.isEmpty(department.getName())) {
            updatedDepartment.setName(department.getName());
        }

        return departmentRepository.save(updatedDepartment);
    }

    public void deleteAll() {
        departmentRepository.deleteAll();
    }

    public List<Department> getAllByNameContainingIgnoreCase(String name) {
        List<Department> allDepartments = departmentRepository.getAllByNameContainingIgnoreCase(name);
        return allDepartments;
    }

    public Department addFacultyToDepartment(Long id, Faculty faculty) {
        Department department = getDepartmentById(id);
        Faculty addFaculty = facultyService.getFacultyById(faculty.getId());
        department.setFaculty(addFaculty);
        return departmentRepository.save(department);
    }
}
