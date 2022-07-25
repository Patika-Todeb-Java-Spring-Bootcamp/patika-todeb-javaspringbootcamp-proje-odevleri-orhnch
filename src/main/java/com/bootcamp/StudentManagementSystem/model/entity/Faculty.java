package com.bootcamp.StudentManagementSystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "faculty")
public class Faculty implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Department> departments;

}
