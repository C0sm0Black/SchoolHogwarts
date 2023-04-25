package ru.hogwarts.school.service;

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

}