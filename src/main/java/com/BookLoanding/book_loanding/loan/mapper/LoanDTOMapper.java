package com.BookLoanding.book_loanding.loan.mapper;

import com.BookLoanding.book_loanding.loan.dto.LoanDTO;
import com.BookLoanding.book_loanding.loan.model.Loan;
import com.BookLoanding.book_loanding.loan.request.BookLoanItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;


import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Component
public class LoanDTOMapper implements Function<Loan, LoanDTO> {

    @Override
    public LoanDTO apply(Loan loan) {

        ObjectMapper mapper = new ObjectMapper();
        List<BookLoanItem> detail;

        try {
            detail = mapper.readValue(loan.getDetailJson(), new TypeReference<List<BookLoanItem>>() {});
        } catch (JsonProcessingException e) {
            detail = Collections.emptyList();
        }

        return new LoanDTO(
                loan.getId(),
                loan.getStudent(),
                loan.getLoanDate(),
                loan.getReturnDate(),
                loan.getTentativeDate(),
                loan.getState(),
                loan.getBooksId(),
                detail
        );
    }
}
