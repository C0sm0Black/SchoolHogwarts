package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    public Student creatStudent (Student student) {
        student.setId(++lastId);
        return students.put(lastId, student);
    }

    public Student findStudent (Long id) {
        return students.get(id);
    }

    public Student editStudent (Student student) {
        return students.put(student.getId(), student);
    }

    public Student deleteStudent (Student student) {
        return students.remove(student);
    }

}
