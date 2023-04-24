package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty (Faculty faculty) {
        faculty.setId(++lastId);
        return faculties.put(lastId, faculty);
    }

    public Faculty findFaculty (Long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty (Faculty faculty) {
        return faculties.put(faculty.getId(), faculty);
    }

    public Faculty deleteFaculty (Long id) {
        return faculties.remove(id);
    }

}
