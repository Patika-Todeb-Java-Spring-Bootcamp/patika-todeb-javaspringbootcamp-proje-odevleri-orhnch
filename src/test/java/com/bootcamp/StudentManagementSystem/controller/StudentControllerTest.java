package com.bootcamp.StudentManagementSystem.controller;

import com.bootcamp.StudentManagementSystem.exception.handler.GenericExceptionHandler;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import com.bootcamp.StudentManagementSystem.model.entity.Student;
import com.bootcamp.StudentManagementSystem.service.StudentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    private MockMvc mvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(studentController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }

    @Test
    void getAllStudents() throws Exception {
        // init test values / given
        List<Student> expectedStudents = getSampleTestStudents();

        // stub - when
        Mockito.when(studentService.getAllStudents()).thenReturn(expectedStudents);

        MockHttpServletResponse response = mvc.perform(get("/api/student/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Student> actualStudents = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<Student>>() {
        });
        assertEquals(expectedStudents.size(), actualStudents.size());
    }

    @Test
    void getStudentByID() throws Exception{
        // init test values
        List<Student> expectedStudents = getSampleTestStudents();

        // stub - given
        Mockito.when(studentService.getStudentById(1L)).thenReturn(expectedStudents.get(0));

        MockHttpServletResponse response = mvc.perform(get("/api/student/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Student actualStudent = new ObjectMapper().readValue(response.getContentAsString(), Student.class);
        assertEquals(expectedStudents.get(0).getFirstName(), actualStudent.getFirstName());
        assertEquals(expectedStudents.get(0).getLastName(), actualStudent.getLastName());
        assertEquals(expectedStudents.get(0).getEmail(), actualStudent.getEmail());
        assertEquals(expectedStudents.get(0).getCourses(), actualStudent.getCourses());
    }


    @Test
    void createNewStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void updateStudent() {
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