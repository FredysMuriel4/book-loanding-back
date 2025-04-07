package com.BookLoanding.book_loanding.student.api;

import com.BookLoanding.book_loanding.category.model.Category;
import com.BookLoanding.book_loanding.student.dto.StudentDTO;
import com.BookLoanding.book_loanding.student.model.Student;
import com.BookLoanding.book_loanding.student.request.StudentRequest;
import com.BookLoanding.book_loanding.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/save")
    public StudentDTO createStudent(@Validated @RequestBody StudentRequest studentRequest) {
        return studentService.storeStudent(studentRequest);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Short id) {

        return studentService.getStudentById(id);
    }

    @PutMapping("/update/{id}")
    public StudentDTO updateStudent(
            @PathVariable Short id,
            @Validated @RequestBody StudentRequest studentRequest
    ) {

        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Short id) {

        return studentService.deleteStudent(id);
    }
}
