package ru.hogwarts.school.service;

import liquibase.pro.packaged.D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    StudentRepository studentRepository;

    @Test
    void createStudent_success() {

        //Подготовка входных данных

        long id = 0;
        String name = "Harry";
        int age = 15;

        Student student = new Student(id, name, age);

        //Подготовка ожидаемого результата

        Student expectedStudent = student;
        expectedStudent.setId(1);

        when(studentRepository.save(student)).thenReturn(expectedStudent);

        //Начало теста

        Student actualStudent = studentService.createStudent(student);
        assertEquals(expectedStudent, actualStudent);
        verify(studentRepository).save(student);
        verifyNoMoreInteractions(studentRepository);

    }

    @Test
    void findStudent_success() {

        //Подготовка входных данных

        long id = 0;
        String name = "Harry";
        int age = 15;

        Student student = new Student(id, name, age);

        long findId = id;

        //Подготовка ожидаемого результата

        Student expectedStudent = student;

        when(studentRepository.getById(findId)).thenReturn((expectedStudent));

        //Начало теста

        Student actualStudent = studentService.findStudent(findId);
        assertEquals(expectedStudent, actualStudent);
        verify(studentRepository).getById(findId);
        verifyNoMoreInteractions(studentRepository);


    }

    @Test
    void editStudent_success() {

        //Подготовка входных данных

        long id = 20;
        String name = "Potter";
        int age = 14;

        Student student = new Student(id, name, age);


        //Подготовка ожидаемого результата

        Student expectedStudent = student;

        when(studentRepository.save(student)).thenReturn(expectedStudent);

        //Начало теста

        Student actualStudent = studentService.editStudent(student);
        assertEquals(expectedStudent, actualStudent);
        verify(studentRepository).save(student);
        verifyNoMoreInteractions(studentRepository);

    }

    @Test
    void getStudentByAge_success() {

        //Подготовка входных данных

        Student studentOne = new Student(1, "Harry", 15);
        Student studentTwo = new Student(20, "Potter", 14);

        List<Student> students = new ArrayList<>();

        students.add(studentOne);
        students.add(studentTwo);

        int findAge = 15;

        //Подготовка ожидаемого результата

        List<Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(studentOne);

        when(studentRepository.findAllByAge(findAge)).thenReturn(expectedStudents);

        //Начало теста

        List<Student> actualStudents = studentService.getStudentsByAge(findAge);
        assertEquals(expectedStudents, actualStudents);
        verify(studentRepository).findAllByAge(findAge);
        verifyNoMoreInteractions(studentRepository);

    }

    @Test
    void getAll_success() {

        //Подготовка входных данных

        Student studentOne = new Student(1, "Harry", 15);
        Student studentTwo = new Student(20, "Potter", 14);

        List<Student> students = new ArrayList<>();

        students.add(studentOne);
        students.add(studentTwo);

        //Подготовка ожидаемого результата

        List<Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(studentOne);
        expectedStudents.add(studentTwo);

        when(studentRepository.findAll()).thenReturn(expectedStudents);

        //Начало теста

        List<Student> actualStudents = studentService.getAll();
        assertEquals(expectedStudents, actualStudents);
        verify(studentRepository).findAll();
        verifyNoMoreInteractions(studentRepository);

    }

    @Test
    void getAmountStudents_success() {

        //Подготовка входных данных

        Long amountStudents = 16l;

        //Подготовка ожидаемого результата

        when(studentRepository.getAmountStudents()).thenReturn(amountStudents);

        //Начало теста

        Long actualAmountStudents = studentService.getAmountStudents();
        assertEquals(amountStudents, actualAmountStudents);
        verify(studentRepository).getAmountStudents();
        verifyNoMoreInteractions(studentRepository);

    }

    @Test
    void getAvgAgeStudents_success() {

        //Подготовка входных данных

        Double avgAgeStudents = 15.5;

        //Подготовка ожидаемого результата

        when(studentRepository.getAvgAgeStudents()).thenReturn(avgAgeStudents);

        //Начало теста

        Double actualAvgAgeStudents = studentService.getAvgAgeStudents();
        assertEquals(avgAgeStudents, actualAvgAgeStudents);
        verify(studentRepository).getAvgAgeStudents();
        verifyNoMoreInteractions(studentRepository);

    }

    @Test
    void getLastStudents_success() {

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
        students.add(studentFive);

        //Подготовка ожидаемого результата

        when(studentRepository.getLastStudents()).thenReturn(students);

        //Начало теста

        List<Student> actualStudents = studentService.getLastStudents();
        assertEquals(students, actualStudents);
        verify(studentRepository).getLastStudents();
        verifyNoMoreInteractions(studentRepository);

    }

    @Test
    void getStudentByNameForFirstA_success() {

        //Подготовка входных данных

        Student studentOne = new Student(1, "Harry Potter", 14);
        Student studentTwo = new Student(2, "Ron Wiesly", 14);
        Student studentThree = new Student(3, "Ron Wiesly", 18);
        Student studentFour = new Student(4, "Emily Taylor", 12);
        Student studentFive = new Student(5, "Amber Noel", 21);

        List<Student> students = new ArrayList<>();
        List<String> studentsWithNameForFirstA = new ArrayList<>();

        students.add(studentOne);
        students.add(studentTwo);
        students.add(studentThree);
        students.add(studentFour);
        students.add(studentFive);

        studentsWithNameForFirstA.add(studentFive.getName().toUpperCase());


        //Подготовка ожидаемого результата

        when(studentRepository.findAll()).thenReturn(students);

        //Начало теста

        List<String> actualStudents = studentService.getStudentByNameForFirstA();
        assertEquals(studentsWithNameForFirstA, actualStudents);
        verify(studentRepository).findAll();
        verifyNoMoreInteractions(studentRepository);

    }

    @Test
    void getAvgAgeStudentsSecondMethod_success() {

        //Подготовка входных данных

        Student studentOne = new Student(1, "Harry Potter", 14);
        Student studentTwo = new Student(2, "Ron Wiesly", 14);
        Student studentThree = new Student(3, "Ron Wiesly", 18);
        Student studentFour = new Student(4, "Emily Taylor", 12);
        Student studentFive = new Student(5, "Amber Noel", 21);

        List<Student> students = new ArrayList<>();

        students.add(studentOne);
        students.add(studentTwo);
        students.add(studentThree);
        students.add(studentFour);
        students.add(studentFive);

        Double expectedAvg = 15.8;


        //Подготовка ожидаемого результата

        when(studentRepository.findAll()).thenReturn(students);

        //Начало теста

        Double actualAvg = studentService.getAvgAgeStudentsSecondMethod();
        assertEquals(expectedAvg, actualAvg);
        verify(studentRepository).findAll();
        verifyNoMoreInteractions(studentRepository);

    }


}