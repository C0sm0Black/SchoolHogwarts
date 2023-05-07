package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {

        logger.info("Was invoked method StudentService::createStudent");
        return studentRepository.save(student);

    }

    public Student findStudent(long id) {

        logger.info("Was invoked method StudentService::findStudent");
        return studentRepository.getById(id);

    }

    public Student editStudent(Student student) {

        logger.info("Was invoked method StudentService::editStudent");
        return studentRepository.save(student);

    }

    public void deleteStudent(long id) {

        logger.info("Was invoked method StudentService::deleteStudent");
        studentRepository.deleteById(id);

    }

    public List<Student> getStudentsByAge(int age) {

        logger.info("Was invoked method StudentService::getStudentsByAge");
        return studentRepository.findAllByAge(age);

    }

    public List<Student> getAll() {

        logger.info("Was invoked method StudentService::getAll");
        return studentRepository.findAll();

    }

    public List<Student> getStudentByAgeBetween(int min, int max) {

        logger.info("Was invoked method StudentService::getStudentByAgeBetween");
        return studentRepository.findAllByAgeBetween(min, max);

    }

    public Faculty getFacultyByStudent(String name) {

        logger.info("Was invoked method StudentService::getFacultyByStudent");

        Student student = studentRepository.findFirstByName(name);
        return student.getFaculty();

    }

    public Long getAmountStudents() {

        logger.info("Was invoked method StudentService::getAmountStudents");
        return studentRepository.getAmountStudents();

    }

    public Double getAvgAgeStudents() {

        logger.info("Was invoked method StudentService::getAvgAgeStudents");
        return studentRepository.getAvgAgeStudents();

    }

    public List<Student> getLastStudents() {

        logger.info("Was invoked method StudentService::getLastStudents");
        return studentRepository.getLastStudents();

    }

}
