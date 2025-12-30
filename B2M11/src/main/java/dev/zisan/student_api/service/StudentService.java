package dev.zisan.student_api.service;

import dev.zisan.student_api.entity.Student;
import dev.zisan.student_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    // Create
    public Student addStudent(Student student){
        return repository.save(student);
    }

    // Read
    public Student getStudentById(Integer id){

        Optional<Student> student = repository.findById(id);

        return student.orElse(null);
    }

    // Read All
    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    // Update
    public Student updateStudent(Student student){
        return repository.save(student);
    }

    // Delete
    public String deleteStudentById(Integer id){
        repository.deleteById(id);
        return "Student with id " + id + " deleted";
    }

}
