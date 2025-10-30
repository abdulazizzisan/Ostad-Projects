package dev.zisan.controller;

import dev.zisan.entity.Student;
import dev.zisan.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/address/{keyword}")
    public ResponseEntity<List<Student>> getStudentsByAddressContaining(@PathVariable String keyword) {
        return ResponseEntity.ok(studentService.getStudentsByAddressContaining(keyword));
    }

    @GetMapping("/roll/{classRoll}")
    public ResponseEntity<Student> getStudentByClassRoll(@PathVariable Integer classRoll) {
        return ResponseEntity.ok(studentService.getStudentByClassRoll(classRoll));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long id) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }


}
