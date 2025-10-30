package dev.zisan.service;

import dev.zisan.entity.Student;
import dev.zisan.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository repository;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Student found with id: " + id));
    }

    public Student addStudent(Student student) {
        repository.save(student);
        return repository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("No Student found with id: " + student.getId()));
    }

    public void deleteStudentById(Long id) {
        repository.deleteById(id);
    }

    public Student updateStudent(Long id, Student student) {
        Student st = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Student found with id: " + id));
        if (student.getName() != null && !student.getName().isBlank()) {
            st.setName(student.getName());
        }
        if (student.getPhone() != null && !student.getPhone().isBlank()) {
            st.setPhone(student.getPhone());
        }
        if (student.getClassRoll() != 0) {
            st.setClassRoll(student.getClassRoll());
        }
        if (student.getAddress() != null && !student.getAddress().isBlank()) {
            st.setAddress(student.getAddress());
        }
        return repository.save(st);
    }

    public List<Student> getStudentsByAddressContaining(String name) {
        return repository.findStudentsByAddressContaining(name);
    }

    public Student getStudentByClassRoll(int classRoll) {
        return repository.findByClassRoll(classRoll)
                .orElseThrow(() -> new RuntimeException("No Student found with class roll: " + classRoll));
    }
}
