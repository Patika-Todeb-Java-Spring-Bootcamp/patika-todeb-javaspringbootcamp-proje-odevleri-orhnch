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
@Table(name = "class")
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
