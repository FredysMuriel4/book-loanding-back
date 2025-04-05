package com.BookLoanding.book_loanding.student.model;

import com.BookLoanding.book_loanding.loan.model.Loan;
import com.BookLoanding.book_loanding.student.request.StudentRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Students")
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "identification_number", nullable = false, unique = true)
    private String identificationNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Loan> loans;

    public Student(StudentRequest studentRequest) {
        this.firstName = studentRequest.getFirstName();
        this.lastName = studentRequest.getLastName();
        this.identificationNumber = studentRequest.getIdentificationNumber();
        this.email = studentRequest.getEmail();
        this.address = studentRequest.getAddress();
    }

    /*@Column(name = "loan_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Bogota")
    private LocalDate loanDate;

    @Column(name = "return_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Bogota")
    private LocalDate returnDate;*/

}
