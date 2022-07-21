package com.bootcamp.StudentManagementSystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer quota;
    //private Integer numberOfStudentChoseCourse;
    private String code;
    private Integer grade;
    private String letterGrade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_class_id")
    private Class courseClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prelector_id")
    private Prelector prelector;


    @ManyToMany(mappedBy = "courses", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Student> students;



}
