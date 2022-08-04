package com.bootcamp.StudentManagementSystem.controller;

import com.bootcamp.StudentManagementSystem.exception.handler.GenericExceptionHandler;
import com.bootcamp.StudentManagementSystem.model.dto.PrelectorDTO;
import com.bootcamp.StudentManagementSystem.model.dto.StudentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import com.bootcamp.StudentManagementSystem.model.entity.Student;
import com.bootcamp.StudentManagementSystem.service.PrelectorService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PrelectorControllerTest {
    private MockMvc mvc;

    @Mock
    private PrelectorService prelectorService;

    @InjectMocks
    private PrelectorController prelectorController;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(prelectorController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }

    @Test
    void getAllPrelectors() throws Exception {
        // init test values / given
        List<Prelector> expectedPrelectors = getSampleTestPrelectors();

        // stub - when
        Mockito.when(prelectorService.getAllPrelectors()).thenReturn(expectedPrelectors);

        MockHttpServletResponse response = mvc.perform(get("/api/prelector/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Prelector> actualPrelectors = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<Prelector>>() {
        });
        assertEquals(expectedPrelectors.size(), actualPrelectors.size());
    }

    @Test
    void getPrelectorByID() throws Exception{
        // init test values
        List<Prelector> expectedPrelectors = getSampleTestPrelectors();

        // stub - given
        Mockito.when(prelectorService.getPrelectorById(1L)).thenReturn(expectedPrelectors.get(0));

        MockHttpServletResponse response = mvc.perform(get("/api/prelector/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Prelector actualPrelector = new ObjectMapper().readValue(response.getContentAsString(), Prelector.class);
        assertEquals(expectedPrelectors.get(0).getFirstName(), actualPrelector.getFirstName());
        assertEquals(expectedPrelectors.get(0).getLastName(), actualPrelector.getLastName());
        assertEquals(expectedPrelectors.get(0).getEmail(), actualPrelector.getEmail());
    }


    @Test
    void createNewPrelectors() throws Exception {
        // init test values
        Prelector expectedPrelector = getSampleTestPrelectors().get(0);
        ObjectMapper inputJson = new ObjectMapper();
        String inner = inputJson.writeValueAsString(expectedPrelector);

        // stub - given
        Mockito.when(prelectorService.create(Mockito.any(PrelectorDTO.class))).thenReturn(expectedPrelector);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/prelector/create")
                .accept(MediaType.APPLICATION_JSON)
                .content(inner)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();


        // then
        assertThat(outputInJson).isEqualTo(inner);
        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
    }

    @Test
    void updatePrelector() {
    }

    @Test
    void deletePrelector() throws Exception {
        // init test values
        willDoNothing().given(prelectorService).delete(1L);

        // stub - given
        ResultActions response = mvc.perform(delete("/api/prelector?id=1"));

        // then
        response.andExpect(status().isOk())
                .andDo(print());
    }


    private List<Prelector> getSampleTestPrelectors() {
        List<Prelector> sampleList = new ArrayList<>();
        Prelector prelector = new Prelector(1L, "Ahmet", "Kara", "ahmetkara@patika", null, null);
        Prelector prelector2 = new Prelector(2L, "Mehmet", "Ak", "mehmetkara@patika", null, null);
        Prelector prelector3 = new Prelector(3L, "Selami", "Gezen", "selamigezen@patika", null, null);
        sampleList.add(prelector);
        sampleList.add(prelector2);
        sampleList.add(prelector3);
        return sampleList;
    }

    private Comparator<Prelector> getPrelectorComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }
}