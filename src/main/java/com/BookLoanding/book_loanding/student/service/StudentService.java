package com.BookLoanding.book_loanding.student.service;

import com.BookLoanding.book_loanding.student.dto.StudentDTO;
import com.BookLoanding.book_loanding.student.mapper.StudentDTOMapper;
import com.BookLoanding.book_loanding.student.model.Student;
import com.BookLoanding.book_loanding.student.repository.StudentRepository;
import com.BookLoanding.book_loanding.student.request.StudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentDTOMapper studentDTOMapper;

    public StudentDTO storeStudent(StudentRequest studentRequest) {

        Student student = new Student(studentRequest);
        Student studentSaved = studentRepository.save(student);

        return studentDTOMapper.apply(studentSaved);
    }

    public Student getStudentById(Short id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public List<StudentDTO> getStudents() {

        return studentRepository.findAll()
                .stream()
                .map(studentDTOMapper)
                .collect(Collectors.toList());
    }
}
