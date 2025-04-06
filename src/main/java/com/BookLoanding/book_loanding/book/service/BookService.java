package com.BookLoanding.book_loanding.book.service;

import com.BookLoanding.book_loanding.book.dto.BookDTO;
import com.BookLoanding.book_loanding.book.mapper.BookDtoMapper;
import com.BookLoanding.book_loanding.book.model.Book;
import com.BookLoanding.book_loanding.book.repository.BookRepository;
import com.BookLoanding.book_loanding.book.request.BookRequest;
import com.BookLoanding.book_loanding.category.model.Category;
import com.BookLoanding.book_loanding.category.repository.CategoryRepository;
import com.BookLoanding.book_loanding.category.service.CategoryService;
import com.BookLoanding.book_loanding.loan.request.BookLoanItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookDtoMapper bookDtoMapper;
    private final CategoryService categoryService;

    public List<BookDTO> getAllBooks() {

        return bookRepository.findAll()
                .stream()
                .map(bookDtoMapper)
                .collect(Collectors.toList());
    }

    public List<Book> getAllbooksById(List<Short> bookIds) {

        return bookRepository.findAllById(bookIds);
    }

    public Book getBookById(Short id) {

        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public BookDTO getBookDtoById(Short id) {

        Book book = getBookById(id);
        return bookDtoMapper.apply(book);
    }

    public BookDTO storeBook(BookRequest bookRequest) {

        List<Category> categories = categoryService.getAllCategoriesById(bookRequest.getCategories());

        Book book = new Book(bookRequest, categories);
        Book bookSaved = bookRepository.save(book);
        return bookDtoMapper.apply(bookSaved);
    }

    public BookDTO updateBook(Short id, BookRequest bookRequest) {

        List<Category> categories = categoryService.getAllCategoriesById(bookRequest.getCategories());
        Book book = getBookById(id);

        book.setTitle(bookRequest.getTitle());
        book.setDescription(bookRequest.getDescription());
        book.setAuthor(bookRequest.getAuthor());
        book.setStock(bookRequest.getStock());
        book.setCategoriesId(categories);
        book.setImageUrl(bookRequest.getImageUrl());

        return bookDtoMapper.apply(book);
    }

    public String deleteBook(Short id) {

        Book book = getBookById(id);
        String title = book.getTitle();

        bookRepository.delete(book);

        return "Book with id: "+id.toString()+" and Title: "+title+" deleted";
    }

    public void validateBookStock(List<BookLoanItem> items) {

        for (BookLoanItem item : items) {
            Book book = getBookById(item.getBookId());

            Integer bookStock = book.getStock();
            if(item.getQuantity() > bookStock) {
                throw new IllegalArgumentException("Not enough stock for one of the chosen books");
            }
        }
    }
}
