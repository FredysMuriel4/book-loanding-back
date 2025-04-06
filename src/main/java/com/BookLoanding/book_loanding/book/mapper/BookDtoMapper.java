package com.BookLoanding.book_loanding.book.mapper;

import com.BookLoanding.book_loanding.book.dto.BookDTO;
import com.BookLoanding.book_loanding.book.model.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookDtoMapper implements Function<Book, BookDTO> {

    @Override
    public BookDTO apply(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                book.getAuthor(),
                book.getStock(),
                book.getCategoriesId(),
                book.getImageUrl()
        );
    }
}
