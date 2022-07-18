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
@Table(name= "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int quota;
    private int numberOfStudentChoseCourse;
    private String code;

    @ManyToOne
    private Class courseClass;

    @ManyToOne
    private Prelector prelector;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.MERGE)
    private List<Student> students;



}
