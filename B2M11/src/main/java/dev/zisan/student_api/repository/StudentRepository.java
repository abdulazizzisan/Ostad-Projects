package dev.zisan.student_api.repository;

import dev.zisan.student_api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // CRUD = Create, Read, Update, Delete
}
