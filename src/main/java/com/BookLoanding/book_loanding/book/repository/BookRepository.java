package com.BookLoanding.book_loanding.book.repository;

import com.BookLoanding.book_loanding.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Short> {
}
