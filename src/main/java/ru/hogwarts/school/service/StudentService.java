package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public List<String> getStudentByNameForFirstA() {

        logger.info("Was invoked method StudentService::getStudentByNameForFirstA");

        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());

    }

    public Double getAvgAgeStudentsSecondMethod() {

        logger.info("Was invoked method StudentService::getAvgAgeStudentsSecondMethod");

        List<Student> students = studentRepository.findAll();
        return students.stream()
                .mapToDouble(Student::getAge)
                .average().orElse(0);

    }

    public Integer task() {

        logger.info("Was invoked method StudentService::task");

        long start = System.currentTimeMillis();
        Integer resultParallel = IntStream.iterate(1, a -> a + 1)
                .limit(7_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);

        long finish = System.currentTimeMillis();

        logger.info("resultParallel: {}, time: {}", resultParallel, finish - start);

        start = System.currentTimeMillis();

        Integer result = Stream.iterate(1, a -> a + 1)
                .limit(7_000_000)
                .reduce(0, (a, b) -> a + b);

        finish = System.currentTimeMillis();

        logger.info("result: {}, time: {}", result, finish - start);

        return resultParallel;

    }

}
