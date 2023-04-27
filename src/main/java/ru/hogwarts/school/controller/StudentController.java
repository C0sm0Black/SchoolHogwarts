package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RequestMapping("student")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity getInfoStudent(@PathVariable long id) {

        Student student = studentService.findStudent(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);

    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("id")
    public ResponseEntity deleteStudent(@PathVariable long id) {

        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity getStudentsByAge(@RequestParam int age) {

        List<Student> students = studentService.getStudentsByAge(age);

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(students);

    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {

        List<Student> students = studentService.getAll();

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(students);

    }

    @GetMapping("/getByAgeBetween")
    public ResponseEntity getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {

        List<Student> students = studentService.getStudentByAgeBetween(min, max);

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(students);

    }

    @GetMapping("/getFacultyByStudent")
    public ResponseEntity getFacultyByStudent(@RequestParam String name) {

        Faculty faculty = studentService.getFacultyByStudent(name);

        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(faculty);

    }

}
