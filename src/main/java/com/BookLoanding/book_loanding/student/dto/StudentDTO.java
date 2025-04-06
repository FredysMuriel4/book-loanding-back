package com.BookLoanding.book_loanding.student.dto;

import com.BookLoanding.book_loanding.loan.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO implements Serializable {

    private Short id;
    private String firstName;
    private String lastName;
    private String identificationNumber;
    private String email;
    private String address;
    private List<Loan> loans;
}
