package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.model.dto.StudentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Class;
import com.bootcamp.StudentManagementSystem.model.entity.Course;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
import com.bootcamp.StudentManagementSystem.model.entity.Student;
import com.bootcamp.StudentManagementSystem.model.mapper.StudentMapper;
import com.bootcamp.StudentManagementSystem.repository.ClassRepository;
import com.bootcamp.StudentManagementSystem.repository.CourseRepository;
import com.bootcamp.StudentManagementSystem.repository.DepartmentRepository;
import com.bootcamp.StudentManagementSystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;

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

    public void deleteAll() {
        studentRepository.deleteAll();
    }


    public List<Student> getAllByFirstNameContainingIgnoreCase(String firstName) {
        List<Student> allStudents = studentRepository.getAllByFirstNameContainingIgnoreCase(firstName);
        return allStudents;
    }

    public Student addClassToStudent(Long id, Class class1) {
        Student student = getStudentById(id);
        Optional<Class> classById = classRepository.findById(class1.getId());
        ///
        if (!classById.isPresent()) {
            return null;
        }
        Class addClass = classById.get();
        // Class addClass = classById.orElse(null);
        student.setClassNumber(addClass);
        return studentRepository.save(student);
    }

    public Student addDepartmentToStudent(Long id, Department department) {
        Student student = getStudentById(id);
        Optional<Department> departmentById = departmentRepository.findById(department.getId());
        if (!departmentById.isPresent()) {
            return null;
        }
        Department addDepartment = departmentById.get();
        student.setDepartment(addDepartment);
        return studentRepository.save(student);
    }

    public Student addCourseToStudent(Long id, Course course) {
        Student student = getStudentById(id);
        Optional<Course> courseById = courseRepository.findById(course.getId());
        if (!courseById.isPresent()) {
            return null;
        }
        Course addCourse = courseById.get();
        List<Course> courses = student.getCourses();
        courses.add(addCourse);
        return studentRepository.save(student);
    }


}
