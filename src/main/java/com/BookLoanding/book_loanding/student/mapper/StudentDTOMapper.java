package com.BookLoanding.book_loanding.student.mapper;

import com.BookLoanding.book_loanding.student.dto.StudentDTO;
import com.BookLoanding.book_loanding.student.model.Student;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StudentDTOMapper implements Function<Student, StudentDTO> {

    @Override
    public StudentDTO apply(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getIdentificationNumber(),
                student.getEmail(),
                student.getAddress(),
                student.getLoans()
        );
    }
}
