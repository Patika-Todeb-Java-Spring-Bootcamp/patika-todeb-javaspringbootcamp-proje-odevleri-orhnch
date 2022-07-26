package com.bootcamp.StudentManagementSystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Class> classes;


    @OneToMany(mappedBy = "department", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy = "department", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Prelector> prelectors;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;
}
