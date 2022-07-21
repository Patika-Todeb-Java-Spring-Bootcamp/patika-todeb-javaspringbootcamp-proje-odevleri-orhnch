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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "courseClass", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Course> courses;

    @OneToMany(mappedBy = "classNumber", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Student> students;
}
