package dev.zisan.b3_m13_student_database_api.controller;

import dev.zisan.b3_m13_student_database_api.entity.Shift;
import dev.zisan.b3_m13_student_database_api.entity.Student;
import dev.zisan.b3_m13_student_database_api.payload.StudentByClassAndAgeRequest;
import dev.zisan.b3_m13_student_database_api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping("/class10-under15")
    private Integer getStudentsFromClass10UnderAge15() {
        return service.getStudentsFromClass10UnderAge15();
    }

    @GetMapping("/class-under-age")
    private Integer getStudentsByClassAndAgeBelow(@RequestBody StudentByClassAndAgeRequest request) {
        return service.getStudentsByClassAndAgeBelow(request);
    }

    @GetMapping
    private List<Student> getStudentByShift(@RequestParam Shift shift){
        return service.getStudentByShift(shift);
    }


    @GetMapping // http://localhost:8080/api/students?shift=Morning&page=0&size=5
    private Page<Student> getStudentByShift(@RequestParam Shift shift, Pageable pageable){
        return service.getStudentByShift(shift, pageable);
    }

    // Example Page Json
    /*
    {
        "content": [
            {
                "id": 1,
                "name": "John Doe",
                "level": 10,
                "rollNo": 5,
                "age": 14,
                "emergencyContact": "+8801754399775",
                "shift": "Morning"
            },
            ...
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true,
                "empty": true
            },
            "pageNumber": 0,
            "pageSize": 5,
            "offset": 0,
            "paged": true,
            "unpaged": false
        },
        "totalPages": 2,
        "totalElements": 10,
        "last": false,
        "size": 5,
        "number": 0,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "first": true,
        "numberOfElements": 5,
        "empty": false
    }
     */
}
