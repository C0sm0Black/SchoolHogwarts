package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.getById(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getFacultiesByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public List<Faculty> getFacultiesByNameOrColor(String s) {
        return facultyRepository.findAllByNameIgnoreCaseOrColorIgnoreCase(s, s);
    }

    public List<Student> getFacultyStudents(String nameFaculty) {
        return facultyRepository.findFirstByName(nameFaculty).getStudents();
    }


}
