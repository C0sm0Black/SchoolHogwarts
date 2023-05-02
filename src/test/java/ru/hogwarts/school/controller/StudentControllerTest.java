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
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getInfoStudent_success() throws Exception {

        //Подготовка входных данных

        long idStudent = 1;
        String nameStudent = "Harry Potter";
        int age = 14;

        Student student = new Student(idStudent, nameStudent, age);

        //Подготовка ожидаемого результата

        when(studentService.findStudent(idStudent)).thenReturn(student);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + idStudent)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void createStudent_success() throws Exception {

        //Подготовка входных данных

        long idStudent = 1;
        String nameStudent = "Harry Potter";
        int age = 14;

        Student student = new Student(idStudent, nameStudent, age);
        String json = new ObjectMapper().writeValueAsString(student);

        //Подготовка ожидаемого результата

        when(studentService.createStudent(student)).thenReturn(student);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void editStudent_success() throws Exception {

        //Подготовка входных данных

        long idStudent = 1;
        String nameStudent = "Harry Potter";
        int age = 14;

        Student student = new Student(idStudent, nameStudent, age);
        String json = new ObjectMapper().writeValueAsString(student);

        //Подготовка ожидаемого результата

        when(studentService.editStudent(student)).thenReturn(student);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void deleteStudent_success() throws Exception {

        //Подготовка входных данных

        long idStudent = 1;
        String nameStudent = "Harry Potter";
        int age = 14;

        Student student = new Student(idStudent, nameStudent, age);

        //Подготовка ожидаемого результата

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + idStudent)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getStudentsByAge_success() throws Exception {

        //Подготовка входных данных

        long idStudent = 1;
        String nameStudent = "Harry Potter";
        int age = 14;

        Student student = new Student(idStudent, nameStudent, age);

        List<Student> students = new ArrayList<>();
        students.add(student);

        //Подготовка ожидаемого результата

        when(studentService.getStudentsByAge(age)).thenReturn(students);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student?age=" + age)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getAll_success() throws Exception {

        //Подготовка входных данных

        long idStudent = 1;
        String nameStudent = "Harry Potter";
        int age = 14;

        Student student = new Student(idStudent, nameStudent, age);

        List<Student> students = new ArrayList<>();
        students.add(student);

        //Подготовка ожидаемого результата

        when(studentService.getAll()).thenReturn(students);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getStudentsByAgeBetween_success() throws Exception {

        //Подготовка входных данных

        long idStudent = 1;
        String nameStudent = "Harry Potter";
        int age = 14;

        int min = 10;
        int max = 15;

        Student student = new Student(idStudent, nameStudent, age);

        List<Student> students = new ArrayList<>();
        students.add(student);

        //Подготовка ожидаемого результата

        when(studentService.getStudentByAgeBetween(min, max)).thenReturn(students);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/getByAgeBetween?min=" + min + "&max=" + max)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getFacultyByStudent_success() throws Exception {

        //Подготовка входных данных

        long idStudent = 1;
        String nameStudent = "Harry Potter";
        int age = 14;

        Student student = new Student(idStudent, nameStudent, age);

        long idFaculty = 10;
        String nameFaculty = "Gryffindor";
        String color = "red";

        Faculty faculty = new Faculty(idFaculty, nameFaculty, color);

        student.setFaculty(faculty);

        //Подготовка ожидаемого результата

        when(studentService.getFacultyByStudent(nameFaculty)).thenReturn(student.getFaculty());

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/getFacultyByStudent?name=" + nameFaculty)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getAmountStudents_success() throws Exception{

        //Подготовка входных данных

        long amountStudents = 1;

        //Подготовка ожидаемого результата

        when(studentService.getAmountStudents()).thenReturn(amountStudents);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/getAmountStudents")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getAvgAgeStudents_success() throws Exception{

        //Подготовка входных данных

        double avgAgeStudents = 14.5;

        //Подготовка ожидаемого результата

        when(studentService.getAvgAgeStudents()).thenReturn(avgAgeStudents);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/getAvgAgeStudents")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getLastStudents_success() throws Exception {

        //Подготовка входных данных

        Student studentOne = new Student(1, "Harry Potter", 14);
        Student studentTwo = new Student(2, "Ron Wiesly", 14);
        Student studentThree = new Student(3, "Amber Noel", 18);
        Student studentFour = new Student(4, "Emily Taylor", 12);
        Student studentFive = new Student(5, "Zubeida Khan", 21);

        List<Student> students = new ArrayList<>();

        students.add(studentOne);
        students.add(studentTwo);
        students.add(studentThree);
        students.add(studentFour);
        students.add(studentFive);;

        //Подготовка ожидаемого результата

        when(studentService.getLastStudents()).thenReturn(students);

        //Начала теста

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/getAvgAgeStudents")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}