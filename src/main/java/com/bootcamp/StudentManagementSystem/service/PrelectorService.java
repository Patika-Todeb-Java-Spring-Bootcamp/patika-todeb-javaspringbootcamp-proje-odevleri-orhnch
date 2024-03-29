package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.exception.EntityNotFoundException;
import com.bootcamp.StudentManagementSystem.model.dto.PrelectorDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import com.bootcamp.StudentManagementSystem.model.mapper.PrelectorMapper;
import com.bootcamp.StudentManagementSystem.repository.PrelectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class PrelectorService {
    private final PrelectorRepository prelectorRepository;
    private final DepartmentService departmentService;

    public List<Prelector> getAllPrelectors() {
        List<Prelector> allPrelectors = prelectorRepository.findAll();
        return allPrelectors;
    }

    public Prelector getPrelectorById(Long id) {
        Optional<Prelector> byId = prelectorRepository.findById(id);
        return byId.orElseThrow(() ->{
            log.error("Prelector could not found by id: " +id);
            return new EntityNotFoundException("Prelector", "id :" + id);
        });
    }

    public Prelector getPrelectorByEmail(String email) {
        Optional<Prelector> prelectorByEmail = prelectorRepository.findPrelectorByEmail(email);
        return prelectorByEmail.orElseThrow(() -> new EntityNotFoundException("Prelector", "email :" + email));
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
        Prelector updatedPrelector = getPrelectorByEmail(email);
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

    public void deleteAll() {
        prelectorRepository.deleteAll();
    }

    public List<Prelector> getAllByFirstNameContainingIgnoreCase(String firstName) {
        List<Prelector> allPrelectors = prelectorRepository.getAllByFirstNameContainingIgnoreCase(firstName);
        return allPrelectors;
    }

    public Prelector addDepartmentToPrelector(Long id, Department department) {
        Prelector prelector = getPrelectorById(id);
        Department addDepartment = departmentService.getDepartmentById(department.getId());
        prelector.setDepartment(addDepartment);
        return prelectorRepository.save(prelector);
    }
}
