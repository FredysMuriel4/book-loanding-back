package com.BookLoanding.book_loanding.loan.api;

import com.BookLoanding.book_loanding.loan.dto.LoanDTO;
import com.BookLoanding.book_loanding.loan.request.LoanRequest;
import com.BookLoanding.book_loanding.loan.request.LoanUpdateRequest;
import com.BookLoanding.book_loanding.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PostMapping("/save")
    public LoanDTO createLoan(@Validated @RequestBody LoanRequest loanRequest) {

        return loanService.storeLoan(loanRequest);
    }

    @PutMapping("/update/{id}")
    public LoanDTO updateLoan(
            @PathVariable Short id,
            @Validated @RequestBody LoanUpdateRequest loanUpdateRequest
    ) {

        return loanService.updateLoan(id, loanUpdateRequest);
    }
}
