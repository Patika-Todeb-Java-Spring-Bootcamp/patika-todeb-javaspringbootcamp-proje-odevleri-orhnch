package com.bootcamp.StudentManagementSystem.model.dto;

import com.bootcamp.StudentManagementSystem.model.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    private List<Role> roles;

}