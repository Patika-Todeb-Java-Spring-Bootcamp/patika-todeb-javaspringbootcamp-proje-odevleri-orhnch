package com.bootcamp.StudentManagementSystem.model.mapper;

import com.bootcamp.StudentManagementSystem.model.dto.ClassDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Class;

public class ClassMapper {
    public static ClassDTO toDTO(Class class1){
        ClassDTO classDTO = new ClassDTO();
        classDTO.setName(class1.getName());
        classDTO.setDepartment(class1.getDepartment());
        return classDTO;
    }

    public static Class toEntity(ClassDTO classDTO){
        Class class1  = new Class();
        class1.setName(classDTO.getName());
        class1.setDepartment(classDTO.getDepartment());
        return class1;
    }
}
