package com.BookLoanding.book_loanding.loan.repository;

import com.BookLoanding.book_loanding.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Short> {
}
