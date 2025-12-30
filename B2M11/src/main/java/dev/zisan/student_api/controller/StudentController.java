package dev.zisan.student_api.controller;

import dev.zisan.student_api.dto.StudentDto;
import dev.zisan.student_api.entity.Student;
import dev.zisan.student_api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public Student createStudent(@RequestBody StudentDto studentDto) {
        Student student = new Student();

        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setRollNo(studentDto.getRollNo());
        student.setLevel(studentDto.getLevel());
        student.setGpa(studentDto.getGpa());

        return service.addStudent(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id){

        Student student = service.getStudentById(id);

        if(student == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(student);
        }
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return service.getAllStudents();
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto){
        Student student = new Student();

        student.setId(id);
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setRollNo(studentDto.getRollNo());
        student.setLevel(studentDto.getLevel());
        student.setGpa(studentDto.getGpa());

        return service.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable Integer id){
        return service.deleteStudentById(id);
    }
}
