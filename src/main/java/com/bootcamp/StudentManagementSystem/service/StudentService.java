package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.model.dto.StudentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Student;
import com.bootcamp.StudentManagementSystem.model.mapper.StudentMapper;
import com.bootcamp.StudentManagementSystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        return allStudents;
    }

    public Student getStudentById(Long id) {
        Optional<Student> byId = studentRepository.findById(id);
        return byId.orElseThrow(() -> new RuntimeException("Student not found!"));
    }

    public Student create(StudentDTO studentDTO) {
        Student student = StudentMapper.toEntity(studentDTO);
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        getStudentById(id);
        studentRepository.deleteById(id);
    }

    public Student update(String email, StudentDTO student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
        if (!studentByEmail.isPresent())
            return null;
        Student updatedStudent = studentByEmail.get();
        if (!StringUtils.isEmpty(student.getEmail())) {
            updatedStudent.setEmail(student.getEmail());
        }
        if (!StringUtils.isEmpty(student.getFirstName())) {
            updatedStudent.setFirstName(student.getFirstName());
        }
        if (!StringUtils.isEmpty(student.getLastName())) {
            updatedStudent.setLastName(student.getLastName());
        }
        return studentRepository.save(updatedStudent);
    }

    public void deleteAll(){
        studentRepository.deleteAll();
    }


    public List<Student> getAllByFirstNameContainingIgnoreCase(String firstName) {
        List<Student> allStudents = studentRepository.getAllByFirstNameContainingIgnoreCase(firstName);
        return allStudents;
    }

}
