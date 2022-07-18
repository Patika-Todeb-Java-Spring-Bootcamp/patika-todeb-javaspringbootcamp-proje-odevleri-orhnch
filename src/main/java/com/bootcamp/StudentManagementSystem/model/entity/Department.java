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
@Table(name= "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.MERGE)
    private List<Class> classes;

    @OneToMany(mappedBy = "department", cascade = CascadeType.MERGE)
    private List<Student> students;

    @OneToMany(mappedBy = "department", cascade = CascadeType.MERGE)
    private List<Prelector> prelectors;

    @ManyToOne
    private Faculty faculty;
}
