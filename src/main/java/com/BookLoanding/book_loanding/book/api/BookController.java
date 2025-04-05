package com.BookLoanding.book_loanding.book.api;

import com.BookLoanding.book_loanding.book.dto.BookDTO;
import com.BookLoanding.book_loanding.book.request.BookRequest;
import com.BookLoanding.book_loanding.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {

        return bookService.getAllBooks();
    }

    @PostMapping("/save")
    public BookDTO createBook(@Validated @RequestBody BookRequest bookRequest) {

        return bookService.storeBook(bookRequest);
    }
}
