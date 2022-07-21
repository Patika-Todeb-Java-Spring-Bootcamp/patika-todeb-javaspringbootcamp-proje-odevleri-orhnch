package com.bootcamp.StudentManagementSystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_class_id")
    private Class courseClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prelector_id")
    private Prelector prelector;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Student> students;



}
