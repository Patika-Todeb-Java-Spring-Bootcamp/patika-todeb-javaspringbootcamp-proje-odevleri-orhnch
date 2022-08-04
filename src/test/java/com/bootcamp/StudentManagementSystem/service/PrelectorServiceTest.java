package com.bootcamp.StudentManagementSystem.service;

import com.bootcamp.StudentManagementSystem.exception.EntityNotFoundException;
import com.bootcamp.StudentManagementSystem.model.dto.PrelectorDTO;
import com.bootcamp.StudentManagementSystem.model.entity.Prelector;
import com.bootcamp.StudentManagementSystem.repository.PrelectorRepository;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PrelectorServiceTest {

    @Mock
    private PrelectorRepository prelectorRepository;

    @InjectMocks
    private PrelectorService prelectorService;

    @Test
    void getAllPrelectors() {
        // init step
        List<Prelector> expPrelectorList = getSampleTestPrelectors();

        // stub - when step
        Mockito.when(prelectorRepository.findAll()).thenReturn(expPrelectorList);

        // then - validate step
        List<Prelector> actualPrelectorList = prelectorService.getAllPrelectors();

        assertEquals(expPrelectorList.size(), actualPrelectorList.size());

        System.out.println("First: " + expPrelectorList);
        expPrelectorList = expPrelectorList.stream().sorted(getPrelectorComparator()).collect(Collectors.toList());
        actualPrelectorList = actualPrelectorList.stream().sorted(getPrelectorComparator()).collect(Collectors.toList());
        for (int i = 0; i < expPrelectorList.size(); i++) {
            Prelector currExpectedPrelector = expPrelectorList.get(i);
            Prelector currActualPrelector = actualPrelectorList.get(i);
            assertEquals(currExpectedPrelector.getId(), currActualPrelector.getId());
            assertEquals(currExpectedPrelector.getFirstName(), currActualPrelector.getFirstName());
            assertEquals(currExpectedPrelector.getLastName(), currActualPrelector.getLastName());
            assertEquals(currExpectedPrelector.getEmail(), currActualPrelector.getEmail());
            // ...
        }

        System.out.println("Second: " + expPrelectorList);
    }

    @Test
    void getPrelectorById_successful() {
        // init step
        Prelector expectedPrelector = getSampleTestPrelectors().get(1);
        Optional<Prelector> optExpectedPrelector = Optional.of(expectedPrelector);

        // stub - when step
        Mockito.when(prelectorRepository.findById(Mockito.any())).thenReturn(optExpectedPrelector);

        // then - validate step
        Prelector actualPrelector = prelectorService.getPrelectorById(1L);
        assertEquals(actualPrelector.getId(), expectedPrelector.getId());
        assertEquals(actualPrelector.getFirstName(), expectedPrelector.getFirstName());
        assertEquals(actualPrelector.getLastName(), expectedPrelector.getLastName());
        assertEquals(actualPrelector.getEmail(), expectedPrelector.getEmail());

    }

    @Test
    void getPrelectorById_NOT_FOUND() {
        // stub - when step
        Mockito.when(prelectorRepository.findById(1L)).thenReturn(Optional.empty());

        // then - validate step
        assertThrows(EntityNotFoundException.class,
                () -> {
                    Prelector actPrelector = prelectorService.getPrelectorById(1L);
                }
        );
    }

    @Test
    void create() {
        // init step
        Prelector expected = getSampleTestPrelectors().get(0);
        expected.setId(null);
        Prelector actual = getSampleTestPrelectors().get(0);

        // stub - when step
        Mockito.when(prelectorRepository.save(expected)).thenReturn(actual);

        // then - validate step
        PrelectorDTO prelectorDTO = new PrelectorDTO();
        prelectorDTO.setFirstName(expected.getFirstName());
        prelectorDTO.setLastName(expected.getLastName());
        prelectorDTO.setEmail(expected.getEmail());
        prelectorService.create(prelectorDTO);

        Mockito.verify(prelectorRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void update() {
        // init step
        Prelector prelector = getSampleTestPrelectors().get(0);

        PrelectorDTO prelectorDTO = new PrelectorDTO();
        prelectorDTO.setEmail("orhancakmak@patika");
        prelectorDTO.setFirstName("Orhan");
        prelectorDTO.setLastName("Cakmak");

        // stub - when step
        given(prelectorRepository.findPrelectorByEmail(prelector.getEmail())).willReturn(Optional.of(prelector));
        prelectorService.update(prelector.getEmail(), prelectorDTO);

        // then - validate step
        assertEquals(prelector.getEmail(), prelectorDTO.getEmail());
        assertEquals(prelector.getFirstName(), prelectorDTO.getFirstName());
        assertEquals(prelector.getLastName(), prelectorDTO.getLastName());
    }

    @Test
    void delete() {
        // init step
        Prelector prelector = getSampleTestPrelectors().get(0);

        // stub - when step
        Mockito.when(prelectorRepository.findById(prelector.getId())).thenReturn(Optional.of(prelector));

        // then - validate step
        prelectorService.delete(prelector.getId());
        verify(prelectorRepository).deleteById(prelector.getId());
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