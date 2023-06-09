package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByAge(int age);
    List<Student> findAllByAgeBetween(int min, int max);
    Student findFirstByName(String name);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Long getAmountStudents();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Double getAvgAgeStudents();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastStudents();

}
