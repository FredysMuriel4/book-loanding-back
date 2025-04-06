package com.BookLoanding.book_loanding.loan.service;


import com.BookLoanding.book_loanding.book.model.Book;
import com.BookLoanding.book_loanding.book.service.BookService;
import com.BookLoanding.book_loanding.loan.dto.LoanDTO;
import com.BookLoanding.book_loanding.loan.mapper.LoanDTOMapper;
import com.BookLoanding.book_loanding.loan.model.Loan;
import com.BookLoanding.book_loanding.loan.repository.LoanRepository;
import com.BookLoanding.book_loanding.loan.request.BookLoanItem;
import com.BookLoanding.book_loanding.loan.request.LoanRequest;
import com.BookLoanding.book_loanding.loan.request.LoanUpdateRequest;
import com.BookLoanding.book_loanding.student.model.Student;
import com.BookLoanding.book_loanding.student.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanDTOMapper loanDTOMapper;
    private final BookService bookService;
    private final StudentService studentService;

    public List<LoanDTO> getAllLoans() {

        return loanRepository.findAll()
                .stream()
                .map(loanDTOMapper)
                .collect(Collectors.toList());
    }

    public LoanDTO storeLoan(LoanRequest loanRequest) {

        Student student = studentService.getStudentById(loanRequest.getStudentId());

        bookService.validateBookStock(loanRequest.getItems());
        studentService.validateHaveMoreThanTwoActivesLoans(student);

        List<Short> bookIds = loanRequest.getItems().stream()
                .map(BookLoanItem::getBookId)
                .collect(Collectors.toList());

        List<Book> books = bookService.getAllbooksById(bookIds);

        Loan loan = new Loan(loanRequest, student, books);
        Loan loanSaved = loanRepository.save(loan);

        subQuantity(loanRequest.getItems());
        storeDetailFormatted(loanSaved, loanRequest.getItems());

        return loanDTOMapper.apply(loanSaved);
    }

    public void subQuantity(List<BookLoanItem> items) {

        for (BookLoanItem item : items) {
            Book book = bookService.getBookById(item.getBookId());

            book.setStock(book.getStock() - item.getQuantity());
        }
    }

    public LoanDTO updateLoan(Short id, LoanUpdateRequest loanUpdateRequest) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        validateBookQuantityReturned(loanUpdateRequest.getItems(), loan);

        loan.setReturnDate(loanUpdateRequest.getReturnDate());
        loan.setState(false);

        LoanDTO loanUpdated = loanDTOMapper.apply(loan);

        restockQuantity(loanUpdateRequest.getItems());

        return loanUpdated;
    }

    public void restockQuantity(List<BookLoanItem> items) {

        for (BookLoanItem item: items) {

            Book book = bookService.getBookById(item.getBookId());
            book.setStock(book.getStock() + item.getQuantity());
        }
    }

    public void storeDetailFormatted(Loan loan, List<BookLoanItem> items) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            String jsonDetail = mapper.writeValueAsString(items);
            loan.setDetailJson(jsonDetail);
            loanRepository.save(loan);
        } catch (JsonProcessingException e) {

            throw new RuntimeException("Error al convertir los detalles a JSON", e);
        }
    }

    public void validateBookQuantityReturned(List<BookLoanItem> items, Loan loan) {

        LoanDTO loanDto = loanDTOMapper.apply(loan);
        List<BookLoanItem> storagedItems = loanDto.getDetailJson();

        if(storagedItems.size() != items.size()) {
            throw new IllegalArgumentException("Returned books do not correspond to the original quantity.");
        }

        for (Short i = 0; i < items.size(); i++) {

            Short storagedQuantity = storagedItems.get(i).getQuantity();
            Short returnedQuantity = items.get(i).getQuantity();

            if(storagedQuantity > returnedQuantity) {
                throw new IllegalArgumentException("Need to return the amount of books loaned.");
            }
        }
    }
}
