package com.BookLoanding.book_loanding.loan.model;

import com.BookLoanding.book_loanding.book.dto.BookDTO;
import com.BookLoanding.book_loanding.book.model.Book;
import com.BookLoanding.book_loanding.loan.request.LoanRequest;
import com.BookLoanding.book_loanding.student.model.Student;
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
@Entity(name = "Loans")
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private Student student;

    @Column(name = "loan_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Bogota")
    private LocalDate loanDate;

    @Column(name = "return_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Bogota")
    private LocalDate returnDate;

    @Column(name = "tentative_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Bogota")
    private LocalDate tentativeDate;

    @Column(name = "state")
    private Boolean state;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "loan_detail",
            joinColumns = @JoinColumn(name = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @JsonIgnore
    private List<Book> booksId;

    @Column(name = "detail_json", length = 1000)
    private String detailJson;

    public Loan(LoanRequest loanRequest, Student student, List<Book> books) {
        this.student = student;
        this.loanDate = loanRequest.getLoanDate();
        this.tentativeDate = loanRequest.getTentativeDate();
        this.state = true;
        this.booksId = books;
    }

}
