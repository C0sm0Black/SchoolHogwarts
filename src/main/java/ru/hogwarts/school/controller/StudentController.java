package ru.hogwarts.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Map;

@RequestMapping("student")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public Student getInfoStudent (@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PostMapping
    public Student createStudent (@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student editStudent (@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("id")
    public Student deleteStudent (@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping
    public Map<Long, Student> getStudentsByAge (@RequestParam int age) {
        return studentService.getStudentsByAge(age);
    }

}
