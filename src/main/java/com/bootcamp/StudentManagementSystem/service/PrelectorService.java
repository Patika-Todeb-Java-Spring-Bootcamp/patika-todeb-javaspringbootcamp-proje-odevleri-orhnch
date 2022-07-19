package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.model.dto.PrelectorDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import com.bootcamp.StudentManagementSystem.model.mapper.PrelectorMapper;
import com.bootcamp.StudentManagementSystem.repository.PrelectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrelectorService {
    private final PrelectorRepository prelectorRepository;

    public List<Prelector> getAllPrelectors() {
        List<Prelector> allPrelectors = prelectorRepository.findAll();
        return allPrelectors;
    }

    public Prelector getPrelectorById(Long id) {
        Optional<Prelector> byId = prelectorRepository.findById(id);
        return byId.orElseThrow(() -> new RuntimeException("Prelector not found!"));
    }

    public Prelector create(PrelectorDTO prelectorDTO) {
        Prelector prelector = PrelectorMapper.toEntity(prelectorDTO);
        return prelectorRepository.save(prelector);
    }
}
