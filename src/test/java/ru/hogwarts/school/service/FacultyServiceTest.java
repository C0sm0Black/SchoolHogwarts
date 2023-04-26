package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {FacultyService.class})
@ExtendWith(SpringExtension.class)
class FacultyServiceTest {

    @Autowired
    private FacultyService facultyService;

    @MockBean
    private FacultyRepository facultyRepository;

    @Test
    void createFaculty_success() {

        //Подготовка входных данных

        long id = 0;
        String name = "gryffindor";
        String color = "red";

        Faculty faculty = new Faculty(id, name, color);

        //Подготовка ожидаемого результата

        Faculty expectedFaculty = faculty;
        expectedFaculty.setId(1);

        when(facultyRepository.save(faculty)).thenReturn(expectedFaculty);

        //Начало теста

        Faculty actualFaculty = facultyService.createFaculty(faculty);
        assertEquals(expectedFaculty, actualFaculty);
        verify(facultyRepository).save(faculty);
        verifyNoMoreInteractions(facultyRepository);

    }

    @Test
    void findFaculty_success() {

        //Подготовка входных данных

        long id = 6;
        String name = "gryffindor";
        String color = "red";

        Faculty faculty = new Faculty(id, name, color);

        long findId = id;

        //Подготовка ожидаемого результата

       Faculty expectedFaculty = faculty;

        when(facultyRepository.getById(findId)).thenReturn(expectedFaculty);

        //Начало теста

        Faculty actualFaculty = facultyService.findFaculty(findId);
        assertEquals(expectedFaculty, actualFaculty);
        verify(facultyRepository).getById(findId);
        verifyNoMoreInteractions(facultyRepository);

    }

    @Test
    void editFaculty_success() {

        //Подготовка входных данных

        long id = 6;
        String name = "asd";
        String color = "asd";

        Faculty faculty = new Faculty(id, name, color);


        //Подготовка ожидаемого результата

        Faculty expectedFaculty = faculty;

        when(facultyRepository.save(faculty)).thenReturn(expectedFaculty);

        //Начало теста

        Faculty actualFaculty = facultyService.editFaculty(faculty);
        assertEquals(expectedFaculty, actualFaculty);
        verify(facultyRepository).save(faculty);
        verifyNoMoreInteractions(facultyRepository);

    }

    @Test
    void getFacultyByColor_success() {
        //Подготовка входных данных

        Faculty facultyOne = new Faculty(1, "gryffindor", "red");
        Faculty facultyTwo = new Faculty(6, "asd", "asd");

        List<Faculty> faculties = new ArrayList<>();

        faculties.add(facultyOne);
        faculties.add(facultyTwo);

        String findColor = "red";

        //Подготовка ожидаемого результата

        List<Faculty> expectedFaculties = new ArrayList<>();
        expectedFaculties.add(facultyOne);

        when(facultyRepository.findAllByColor(findColor)).thenReturn(expectedFaculties);

        //Начало теста

        List<Faculty> actualFaculties = facultyService.getFacultiesByColor(findColor);
        assertEquals(expectedFaculties, actualFaculties);
        verify(facultyRepository).findAllByColor(findColor);
        verifyNoMoreInteractions(facultyRepository);

    }

    @Test
    void getAll_success() {
        //Подготовка входных данных

        Faculty facultyOne = new Faculty(1, "gryffindor", "red");
        Faculty facultyTwo = new Faculty(6, "asd", "asd");

        List<Faculty> faculties = new ArrayList<>();

        faculties.add(facultyOne);
        faculties.add(facultyTwo);

        //Подготовка ожидаемого результата

        List<Faculty> expectedFaculties = new ArrayList<>();
        expectedFaculties.add(facultyOne);
        expectedFaculties.add(facultyTwo);

        when(facultyRepository.findAll()).thenReturn(expectedFaculties);

        //Начало теста

        List<Faculty> actualFaculties = facultyService.getAll();
        assertEquals(expectedFaculties, actualFaculties);
        verify(facultyRepository).findAll();
        verifyNoMoreInteractions(facultyRepository);

    }


}