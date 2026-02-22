package dev.zisan.b3_m13_student_database_api.service;

import dev.zisan.b3_m13_student_database_api.entity.Shift;
import dev.zisan.b3_m13_student_database_api.entity.Student;
import dev.zisan.b3_m13_student_database_api.payload.StudentByClassAndAgeRequest;
import dev.zisan.b3_m13_student_database_api.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public Integer getStudentsFromClass10UnderAge15() {
        return repository.countStudentsFromClass10UnderAge15();
    }

    public List<Student> getStudentByShift(Shift shift) {
        return repository.findByShift(shift);
    }

    public Page<Student> getStudentByShift(Shift shift, Pageable pageable) {
        return repository.findByShift(shift, pageable);
    }

    public Integer getStudentsByClassAndAgeBelow(StudentByClassAndAgeRequest request) {
        return repository.countByLevelAndAgeLessThan(request.getLevel(), request.getAge());
    }
}
