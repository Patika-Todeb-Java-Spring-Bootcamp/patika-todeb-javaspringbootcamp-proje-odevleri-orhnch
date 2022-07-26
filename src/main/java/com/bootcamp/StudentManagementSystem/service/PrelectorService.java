package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.exception.EntityNotFoundException;
import com.bootcamp.StudentManagementSystem.model.dto.PrelectorDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import com.bootcamp.StudentManagementSystem.model.entity.Student;
import com.bootcamp.StudentManagementSystem.model.mapper.PrelectorMapper;
import com.bootcamp.StudentManagementSystem.repository.DepartmentRepository;
import com.bootcamp.StudentManagementSystem.repository.PrelectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrelectorService {
    private final PrelectorRepository prelectorRepository;
    private final DepartmentRepository departmentRepository;

    public List<Prelector> getAllPrelectors() {
        List<Prelector> allPrelectors = prelectorRepository.findAll();
        return allPrelectors;
    }

    public Prelector getPrelectorById(Long id) {
        Optional<Prelector> byId = prelectorRepository.findById(id);
        return byId.orElseThrow(() -> new EntityNotFoundException("Prelector","id :" + id));
    }

    public Prelector create(PrelectorDTO prelectorDTO) {
        Prelector prelector = PrelectorMapper.toEntity(prelectorDTO);
        return prelectorRepository.save(prelector);
    }

    public void delete(Long id) {
        getPrelectorById(id);
        prelectorRepository.deleteById(id);
    }

    public Prelector update(String email, PrelectorDTO prelector) {
        Optional<Prelector> prelectorByEmail = prelectorRepository.findPrelectorByEmail(email);
        if (!prelectorByEmail.isPresent())
            throw new EntityNotFoundException("Prelector","email :" + email);
        Prelector updatedPrelector = prelectorByEmail.get();
        if (!StringUtils.isEmpty(prelector.getEmail())) {
            updatedPrelector.setEmail(prelector.getEmail());
        }
        if (!StringUtils.isEmpty(prelector.getFirstName())) {
            updatedPrelector.setFirstName(prelector.getFirstName());
        }
        if (!StringUtils.isEmpty(prelector.getLastName())) {
            updatedPrelector.setLastName(prelector.getLastName());
        }

        return prelectorRepository.save(updatedPrelector);
    }

    public void deleteAll(){
        prelectorRepository.deleteAll();
    }

    public List<Prelector> getAllByFirstNameContainingIgnoreCase(String firstName) {
        List<Prelector> allPrelectors = prelectorRepository.getAllByFirstNameContainingIgnoreCase(firstName);
        return allPrelectors;
    }

    public Prelector addDepartmentToPrelector (Long id, Department department){
        Prelector prelector = getPrelectorById(id);
        Optional<Department> departmentById = departmentRepository.findById(department.getId());
        if(!departmentById.isPresent()){
            return null;
        }
        Department addDepartment = departmentById.get();
        prelector.setDepartment(addDepartment);
        return prelectorRepository.save(prelector);
    }
}
