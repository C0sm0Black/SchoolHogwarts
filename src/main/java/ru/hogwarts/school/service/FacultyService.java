package ru.hogwarts.school.service;

import liquibase.pro.packaged.F;
import liquibase.pro.packaged.S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class FacultyService {

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {

        logger.info("Was invoked method FacultyService::createFaculty");
        return facultyRepository.save(faculty);

    }

    public Faculty findFaculty(long id) {

        logger.info("Was invoked method FacultyService::findFaculty");
        return facultyRepository.getById(id);

    }

    public Faculty editFaculty(Faculty faculty) {

        logger.info("Was invoked method FacultyService::editFaculty");
        return facultyRepository.save(faculty);

    }

    public void deleteFaculty(long id) {

        logger.info("Was invoked method FacultyService::deleteFaculty");
        facultyRepository.deleteById(id);

    }

    public List<Faculty> getFacultiesByColor(String color) {

        logger.info("Was invoked method FacultyService::getFacultiesByColor");
        return facultyRepository.findAllByColor(color);

    }

    public List<Faculty> getAll() {

        logger.info("Was invoked method FacultyService::getAll");
        return facultyRepository.findAll();

    }

    public List<Faculty> getFacultiesByNameOrColor(String s) {

        logger.info("Was invoked method FacultyService::getFacultiesByNameOrColor");
        return facultyRepository.findAllByNameIgnoreCaseOrColorIgnoreCase(s, s);

    }

    public List<Student> getFacultyStudents(String nameFaculty) {

        logger.info("Was invoked method FacultyService::getFacultyStudents");
        return facultyRepository.findFirstByName(nameFaculty).getStudents();

    }

    public String getBiggestNameFaculty() {

        logger.info("Was invoked method FacultyService::getBiggestNameFaculty");

        List<Faculty> faculties = facultyRepository.findAll();

        return faculties.stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse(null);

    }


}
