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
        faculties.put(lastId, faculty);
        return faculty;

    }

    public Faculty findFaculty (Long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty (Faculty faculty) {

        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty (Long id) {
        return faculties.remove(id);
    }

    public Map<Long, Faculty> getFacultiesByColor (String color) {

        Map<Long, Faculty> facultiesByColor = new HashMap<>();

        faculties.forEach((k, v) -> {

            if (v.getColor().equals(color)) {
                faculties.put(k, v);
            }

        });

        return facultiesByColor;

    }

}
