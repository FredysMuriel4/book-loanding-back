package com.BookLoanding.book_loanding.book.dto;

import com.BookLoanding.book_loanding.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO implements Serializable {

    private Short id;
    private String title;
    private String description;
    private String author;
    private Integer stock;
    private List<Category> categories;
    private String imageUrl;
}
