package com.BookLoanding.book_loanding.category.mapper;

import com.BookLoanding.book_loanding.category.dto.CategoryDTO;
import com.BookLoanding.book_loanding.category.model.Category;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {

    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
