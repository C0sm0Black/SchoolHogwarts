package ru.hogwarts.school.controller;

import liquibase.pro.packaged.R;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import javax.persistence.criteria.CriteriaBuilder;
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

    @DeleteMapping("{id}")
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

    @GetMapping("/getAmountStudents")
    public ResponseEntity getAmountStudents() {

        Long amount = studentService.getAmountStudents();

        if (amount == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(amount);

    }

    @GetMapping("/getAvgAgeStudents")
    public ResponseEntity getAvgAgeStudents() {

        Double avg = studentService.getAvgAgeStudents();

        if (avg == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(avg);

    }

    @GetMapping("/getLastStudents")
    public ResponseEntity getLastStudents() {

        List<Student> lastStudents = studentService.getLastStudents();

        if (lastStudents.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lastStudents);

    }

    @GetMapping("/getStudentsByNameForFirstA")
    public ResponseEntity getStudentsByNameForFirstA() {

        List<String> names = studentService.getStudentByNameForFirstA();

        if (names.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(names);

    }

    @GetMapping("/getAvgAgeStudentsSecondMethod")
    public ResponseEntity getAvgAgeStudentsSecondMethod() {

        Double avg = studentService.getAvgAgeStudentsSecondMethod();

        if (avg == null || avg == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(avg);

    }

    @GetMapping("/task")
    public ResponseEntity task() {

        Integer sum = studentService.task();

        if (sum == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sum);

    }

    @GetMapping("/printSixStudent")
    public void printSixStudent() {
        studentService.printSixStudent();
    }

    @GetMapping("/printSixStudentSync")
    public void printSixStudentSync() {
        studentService.printSixStudentSynchronized();
    }

}
