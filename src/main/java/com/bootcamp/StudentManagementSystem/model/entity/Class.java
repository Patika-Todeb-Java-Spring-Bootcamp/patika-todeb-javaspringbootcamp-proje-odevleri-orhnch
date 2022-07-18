package com.bootcamp.StudentManagementSystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "courseClass", cascade = CascadeType.MERGE)
    private List<Course> courses;

    @OneToMany(mappedBy = "classNumber", cascade = CascadeType.MERGE)
    private List<Student> students;
}
