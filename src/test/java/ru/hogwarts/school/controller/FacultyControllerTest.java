package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Test
    void getAll_success() throws Exception {

        //Подготовка входных данных

        long id = 1;
        String name = "Gryffindor";
        String color = "red";

        List<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty(id, name, color));

        //Подготовка ожидаемого результата

        when(facultyService.getAll()).thenReturn(faculties);

        //Начала теста
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void createFaculty_success() throws Exception {

        //Подготовка входных данных

        long id = 10;
        String name = "Gryffindor";
        String color = "red";

        Faculty faculty = new Faculty(id, name, color);
        String json = new ObjectMapper().writeValueAsString(faculty);


        //Подготовка ожидаемого результата

        when(facultyService.createFaculty(faculty)).thenReturn(new Faculty(1, name, color));

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void editFaculty_success() throws Exception {

        //Подготовка входных данных

        long id = 10;
        String name = "Gryffindor";
        String color = "red";

        Faculty faculty = new Faculty(id, name, color);
        String json = new ObjectMapper().writeValueAsString(faculty);

        //Подготовка ожидаемого результата

        when(facultyService.editFaculty(faculty)).thenReturn(faculty);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void deleteFaculty_success() throws Exception {

        //Подготовка входных данных

        long id = 10;

        //Подготовка ожидаемого результата

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id)
                        .content(String.valueOf(anyLong())))
                .andExpect(status().isOk());

    }

    @Test
    void getFacultyInfo_success() throws Exception {

        //Подготовка входных данных

        long id = 1;
        String name = "Gryffindor";
        String color = "red";

        Faculty faculty = new Faculty(id, name, color);

        //Подготовка ожидаемого результата

        when(facultyService.findFaculty(id)).thenReturn(faculty);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getFacultiesByColor_success() throws Exception {

        //Подготовка входных данных

        long id = 1;
        String name = "Gryffindor";
        String color = "red";

        List<Faculty> faculties = new ArrayList<>();
        Faculty faculty = new Faculty(id, name, color);

        faculties.add(faculty);


        //Подготовка ожидаемого результата

        when(facultyService.getFacultiesByColor(color)).thenReturn(faculties);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?color=" + color)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getFacultiesByNameOrColor_success() throws Exception {

        long id = 1;
        String name = "Gryffindor";
        String color = "red";

        List<Faculty> faculties = new ArrayList<>();
        Faculty faculty = new Faculty(id, name, color);

        faculties.add(faculty);


        //Подготовка ожидаемого результата

        when(facultyService.getFacultiesByNameOrColor(color)).thenReturn(faculties);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/getFacultiesByNameOrColor?s=" + color)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getFacultyStudents_success() throws Exception {

        long id = 1;
        String nameFaculty = "Gryffindor";
        String color = "red";

        Faculty faculty = new Faculty(id, nameFaculty, color);

        long idStudent = 1;
        String nameStudent = "Harry Potter";
        int age = 14;

        Student student = new Student(idStudent, nameStudent, age);
        List<Student> students = new ArrayList<>();
        students.add(student);

        faculty.setStudents(students);

        //Подготовка ожидаемого результата

        when(facultyService.getFacultyStudents(nameFaculty)).thenReturn(students);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/getFacultyStudents?nameFaculty=" + nameFaculty)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}