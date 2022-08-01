package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.exception.EntityNotFoundException;
import com.bootcamp.StudentManagementSystem.model.dto.DepartmentDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Department;
import com.bootcamp.StudentManagementSystem.repository.DepartmentRepository;
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
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;


    @Test
    void getAllDepartments() {
        // init step
        List<Department> expDepartmentList = getSampleTestDepartments();

        // stub - when step
        Mockito.when(departmentRepository.findAll()).thenReturn(expDepartmentList);

        // then - validate step
        List<Department> actualDepartmentList = departmentService.getAllDepartments();

        assertEquals(expDepartmentList.size(), actualDepartmentList.size());

        System.out.println("First: " + expDepartmentList);
        expDepartmentList = expDepartmentList.stream().sorted(getDepartmentComparator()).collect(Collectors.toList());
        actualDepartmentList = actualDepartmentList.stream().sorted(getDepartmentComparator()).collect(Collectors.toList());
        for (int i = 0; i < expDepartmentList.size(); i++) {
            Department currExpectedDepartment = expDepartmentList.get(i);
            Department currActualDepartment = actualDepartmentList.get(i);
            assertEquals(currExpectedDepartment.getId(), currActualDepartment.getId());
            assertEquals(currExpectedDepartment.getName(), currActualDepartment.getName());
            // ...
        }

        System.out.println("Second: " + expDepartmentList);
    }

    @Test
    void getDepartmentById_successful() {
        // init step
        Department expectedDepartment = getSampleTestDepartments().get(1);
        Optional<Department> optExpectedDepartment = Optional.of(expectedDepartment);

        // stub - when step
        Mockito.when(departmentRepository.findById(Mockito.any())).thenReturn(optExpectedDepartment);

        // then - validate step
        Department actualDepartment = departmentService.getDepartmentById(1L);

        assertEquals(actualDepartment.getId(), expectedDepartment.getId());
        assertEquals(actualDepartment.getName(), expectedDepartment.getName());
        // ...
    }

    @Test
    void getDepartmentById_NOT_FOUND() {
        // stub - when step
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        // then - validate step
        assertThrows(EntityNotFoundException.class,
                () -> {
                    Department actDepartment = departmentService.getDepartmentById(1L);
                }
        );
    }


    @Test
    void create() {
        // init step
        Department expected = getSampleTestDepartments().get(0);
        expected.setId(null);
        Department actual = getSampleTestDepartments().get(0);

        // stub - when step
        Mockito.when(departmentRepository.save(expected)).thenReturn(actual);

        // then - validate step
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName(expected.getName());
        departmentService.create(departmentDTO);

        Mockito.verify(departmentRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void delete() {
        // init step

        // stub - when step

        // then - validate step
    }

    @Test
    void update() {
        // init step

        // stub - when step

        // then - validate step
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