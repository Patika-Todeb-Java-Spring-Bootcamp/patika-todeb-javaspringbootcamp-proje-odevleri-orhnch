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
@Table(name= "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Class classNumber;

    @ManyToMany
    private List<Course> courses;

}
