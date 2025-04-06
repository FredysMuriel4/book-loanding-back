package com.BookLoanding.book_loanding.book.api;

import com.BookLoanding.book_loanding.book.dto.BookDTO;
import com.BookLoanding.book_loanding.book.model.Book;
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

    @PutMapping("/update/{id}")
    public BookDTO updateBook(
            @PathVariable Short id,
            @Validated @RequestBody BookRequest bookRequest
    ) {

        return bookService.updateBook(id, bookRequest);
    }

    @PostMapping("/save")
    public BookDTO createBook(@Validated @RequestBody BookRequest bookRequest) {

        return bookService.storeBook(bookRequest);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Short id) {

        return bookService.deleteBook(id);
    }
}
