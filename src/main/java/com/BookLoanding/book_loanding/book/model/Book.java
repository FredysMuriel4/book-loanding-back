package com.BookLoanding.book_loanding.book.model;

import javax.persistence.*;

import com.BookLoanding.book_loanding.book.request.BookRequest;
import com.BookLoanding.book_loanding.category.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Books")
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnore
    private List<Category> categoriesId;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    public Book(BookRequest bookRequest, List<Category> categories) {
        this.title = bookRequest.getTitle();
        this.description = bookRequest.getDescription();
        this.author = bookRequest.getAuthor();
        this.stock = bookRequest.getStock();
        this.categoriesId = categories;
        this.imageUrl = bookRequest.getImageUrl();
    }
}
