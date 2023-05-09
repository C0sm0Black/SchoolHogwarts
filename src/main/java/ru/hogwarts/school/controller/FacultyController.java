package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RequestMapping("faculty")
@RestController
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity getFacultyInfo(@PathVariable long id) {

        Faculty faculty = facultyService.findFaculty(id);

        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(faculty);

    }

    @PostMapping
    public Faculty createFaculty(Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable long id) {

        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity getFacultiesByColor(@RequestParam String color) {

        List<Faculty> faculties = facultyService.getFacultiesByColor(color);

        if (faculties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(faculties);

    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {

        List<Faculty> faculties = facultyService.getAll();

        if (faculties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(faculties);

    }

    @GetMapping("/getFacultiesByNameOrColor")
    public ResponseEntity getFacultiesByNameOrColor(@RequestParam String s) {

        List<Faculty> faculties = facultyService.getFacultiesByNameOrColor(s);

        if (faculties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(faculties);

    }

    @GetMapping("/getFacultyStudents")
    public ResponseEntity getFacultyStudents(@RequestParam String nameFaculty) {

        List<Student> students = facultyService.getFacultyStudents(nameFaculty);

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(students);

    }

    @GetMapping("/getBiggestNameFaculty")
    public ResponseEntity getBiggestNameFaculty() {

        String name = facultyService.getBiggestNameFaculty();

        if (name == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(name);

    }

}
