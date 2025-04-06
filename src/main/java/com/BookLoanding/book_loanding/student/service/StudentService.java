package com.BookLoanding.book_loanding.student.service;

import com.BookLoanding.book_loanding.loan.model.Loan;
import com.BookLoanding.book_loanding.loan.service.LoanService;
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

    public StudentDTO updateStudent(Short id, StudentRequest studentRequest) {

        Student student = getStudentById(id);

        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setIdentificationNumber(studentRequest.getIdentificationNumber());
        student.setEmail(studentRequest.getEmail());
        student.setAddress(studentRequest.getAddress());

        return studentDTOMapper.apply(student);
    }

    public String deleteStudent(Short id) {

        Student student = getStudentById(id);
        String name = student.getFirstName()+" "+student.getLastName();

        studentRepository.delete(student);

        return "Student called: "+name+" deleted";
    }

    public void validateHaveMoreThanTwoActivesLoans(Student student) {

        List<Loan> loans = student.getLoans();

        Short activeQty = 0;
        for (Loan loan: loans) {

            if(loan.getState()) {
                activeQty++;
            }
        }

        if(activeQty >= 2) {
            throw new IllegalArgumentException("You cannot have more than 2 active loans.");
        }
    }
}
