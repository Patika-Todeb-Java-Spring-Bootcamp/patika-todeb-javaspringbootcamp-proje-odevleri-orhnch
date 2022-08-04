package com.bootcamp.StudentManagementSystem.controller;

import com.bootcamp.StudentManagementSystem.exception.handler.GenericExceptionHandler;
import com.bootcamp.StudentManagementSystem.model.dto.DepartmentDTO;
import com.bootcamp.StudentManagementSystem.model.dto.PrelectorDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import com.bootcamp.StudentManagementSystem.service.DepartmentService;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {
    private MockMvc mvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(departmentController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }

    @Test
    void getAllDepartments() throws Exception {
        // init test values / given
        List<Department> expectedDepartments = getSampleTestDepartments();

        // stub - when
        Mockito.when(departmentService.getAllDepartments()).thenReturn(expectedDepartments);

        MockHttpServletResponse response = mvc.perform(get("/api/department/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Department> actualDepartments = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<Department>>() {
        });
        assertEquals(expectedDepartments.size(), actualDepartments.size());
    }

    @Test
    void getDepartmentById() throws Exception {
        // init test values
        List<Department> expectedDepartments = getSampleTestDepartments();

        // stub - given
        Mockito.when(departmentService.getDepartmentById(1L)).thenReturn(expectedDepartments.get(0));

        MockHttpServletResponse response = mvc.perform(get("/api/department/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Department actualDepartment = new ObjectMapper().readValue(response.getContentAsString(), Department.class);
        assertEquals(expectedDepartments.get(0).getName(), actualDepartment.getName());
    }

    @Test
    void createNewDepartment() throws Exception {
        // init test values
        Department expectedDepartment = getSampleTestDepartments().get(0);
        ObjectMapper inputJson = new ObjectMapper();
        String inner = inputJson.writeValueAsString(expectedDepartment);

        // stub - given
        Mockito.when(departmentService.create(Mockito.any(DepartmentDTO.class))).thenReturn(expectedDepartment);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/department/create")
                .accept(MediaType.APPLICATION_JSON)
                .content(inner)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();


        // then
        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
        assertThat(outputInJson).isEqualTo(inner);
    }

    @Test
    void updateDepartment() {
    }

    @Test
    void deleteDepartment() throws Exception {
        // init test values
        willDoNothing().given(departmentService).delete(1L);

        // stub - given
        ResultActions response = mvc.perform(delete("/api/department?id=1"));

        // then
        response.andExpect(status().isOk())
                .andDo(print());
    }


    private List<Department> getSampleTestDepartments() {
        List<Department> sampleList = new ArrayList<>();
        Department department = new Department(1L, "Civil Engineering", null, null, null, null);
        Department department2 = new Department(2L, "Chemical Engineering", null, null, null, null);
        Department department3 = new Department(3L, "Computer Engineering", null, null, null, null);
        sampleList.add(department);
        sampleList.add(department2);
        sampleList.add(department3);
        return sampleList;
    }

    private Comparator<Department> getDepartmentComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }
}