package com.BookLoanding.book_loanding.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
}
