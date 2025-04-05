package com.BookLoanding.book_loanding.loan.dto;

import com.BookLoanding.book_loanding.book.model.Book;
import com.BookLoanding.book_loanding.loan.request.BookLoanItem;
import com.BookLoanding.book_loanding.student.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO implements Serializable {

    Short id;
    Student student;
    LocalDate loanDate;
    LocalDate returnDate;
    LocalDate tentativeDate;
    Boolean state;
    List<Book> books;
    List<BookLoanItem> detailJson;
}
