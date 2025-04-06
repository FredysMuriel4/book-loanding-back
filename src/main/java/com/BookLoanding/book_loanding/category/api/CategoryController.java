package com.BookLoanding.book_loanding.category.api;

import com.BookLoanding.book_loanding.book.dto.BookDTO;
import com.BookLoanding.book_loanding.book.request.BookRequest;
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

    @PutMapping("/update/{id}")
    public CategoryDTO updateCategory(
            @PathVariable Short id,
            @Validated @RequestBody CategoryRequest categoryRequest
    ) {

        return categoryService.updateCategory(id, categoryRequest);
    }

    @PostMapping("/save")
    public CategoryDTO createCategory(@Validated @RequestBody CategoryRequest categoryRequest) {

        return categoryService.storeCategory(categoryRequest);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Short id) {

        return categoryService.deleteCategory(id);
    }
}
