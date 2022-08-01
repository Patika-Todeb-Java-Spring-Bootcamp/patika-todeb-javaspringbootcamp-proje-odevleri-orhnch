package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.exception.EntityNotFoundException;
import com.bootcamp.StudentManagementSystem.model.dto.StudentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Student;
import com.bootcamp.StudentManagementSystem.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getAllStudents() {
        // init step
        List<Student> expStudentList = getSampleTestStudents();

        // stub - when step
        Mockito.when(studentRepository.findAll()).thenReturn(expStudentList);

        // then - validate step
        List<Student> actualStudentList = studentService.getAllStudents();

        assertEquals(expStudentList.size(), actualStudentList.size());

        System.out.println("First: " + expStudentList);
        expStudentList = expStudentList.stream().sorted(getStudentComparator()).collect(Collectors.toList());
        actualStudentList = actualStudentList.stream().sorted(getStudentComparator()).collect(Collectors.toList());
        for (int i = 0; i < expStudentList.size(); i++) {
            Student currExpectedStudent = expStudentList.get(i);
            Student currActualStudent = actualStudentList.get(i);
            assertEquals(currExpectedStudent.getId(), currActualStudent.getId());
            assertEquals(currExpectedStudent.getFirstName(), currActualStudent.getFirstName());
            assertEquals(currExpectedStudent.getLastName(), currActualStudent.getLastName());
            assertEquals(currExpectedStudent.getEmail(), currActualStudent.getEmail());
            // ...
        }

        System.out.println("Second: " + expStudentList);
    }

    @Test
    void getStudentById_successful() {
        // init step
        Student expectedStudent = getSampleTestStudents().get(1);
        Optional<Student> optExpectedStudent = Optional.of(expectedStudent);

        // stub - when step
        Mockito.when(studentRepository.findById(Mockito.any())).thenReturn(optExpectedStudent);

        // then - validate step
        Student actualStudent = studentService.getStudentById(1L);
        assertEquals(actualStudent.getId(), expectedStudent.getId());
        assertEquals(actualStudent.getFirstName(), expectedStudent.getFirstName());
        assertEquals(actualStudent.getLastName(), expectedStudent.getLastName());
        assertEquals(actualStudent.getEmail(), expectedStudent.getEmail());
    }

    @Test
    void getStudentById_NOT_FOUND() {
        // stub - when step
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // then - validate step
        assertThrows(EntityNotFoundException.class,
                () -> {
                    Student actStudent = studentService.getStudentById(1L);
                }
        );
    }

    @Test
    void create() {
        // init step
        Student expected = getSampleTestStudents().get(0);
        expected.setId(null);
        Student actual = getSampleTestStudents().get(0);

        // stub - when step
        Mockito.when(studentRepository.save(expected)).thenReturn(actual);

        // then - validate step
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(expected.getFirstName());
        studentDTO.setLastName(expected.getLastName());
        studentDTO.setEmail(expected.getEmail());
        studentService.create(studentDTO);

        Mockito.verify(studentRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }


    private List<Student> getSampleTestStudents() {
        List<Student> sampleList = new ArrayList<>();
        Student student = new Student(1L, "Orhan", "Cakmak", "orhancakmak@patika", null, null,new ArrayList<>());
        Student student2 = new Student(2L, "Ali", "Veli", "aliveli@patika", null, null,new ArrayList<>());
        Student student3 = new Student(3L, "Mehmet", "Genc", "mehmetgenc@patika", null, null,new ArrayList<>());
        sampleList.add(student);
        sampleList.add(student2);
        sampleList.add(student3);
        return sampleList;
    }

    private Comparator<Student> getStudentComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }
}