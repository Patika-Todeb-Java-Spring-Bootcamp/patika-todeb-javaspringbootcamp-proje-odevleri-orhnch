package com.bootcamp.StudentManagementSystem.model.mapper;

import com.bootcamp.StudentManagementSystem.model.dto.PrelectorDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;

public class PrelectorMapper {
    public static PrelectorDTO toDTO(Prelector prelector){
        PrelectorDTO prelectorDTO = new PrelectorDTO();
        prelectorDTO.setFirstName(prelector.getFirstName());
        prelectorDTO.setLastName(prelector.getLastName());
        prelectorDTO.setEmail(prelector.getEmail());
        return prelectorDTO;
    }
    public static Prelector toEntity(PrelectorDTO prelectorDTO){
        Prelector prelector = new Prelector();
        prelector.setFirstName(prelectorDTO.getFirstName());
        prelector.setLastName(prelectorDTO.getLastName());
        prelector.setEmail(prelectorDTO.getEmail());
        return prelector;
    }
}
