package com.bootcamp.StudentManagementSystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/*
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
*/
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "class")
public class Class implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer level;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "courseClass", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Course> courses;

    @OneToMany(mappedBy = "classNumber", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Student> students;
}
