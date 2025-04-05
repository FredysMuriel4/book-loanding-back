package com.BookLoanding.book_loanding.category.api;

import com.BookLoanding.book_loanding.category.dto.CategoryDTO;
import com.BookLoanding.book_loanding.category.request.CategoryRequest;
import com.BookLoanding.book_loanding.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public List<CategoryDTO> getAllCategories() {

        return categoryService.getAllCategories();
    }

    @PostMapping("/save")
    public CategoryDTO createCategory(@Validated @RequestBody CategoryRequest categoryRequest) {

        return categoryService.storeCategory(categoryRequest);
    }
}
