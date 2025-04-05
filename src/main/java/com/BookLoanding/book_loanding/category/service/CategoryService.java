package com.BookLoanding.book_loanding.category.service;

import com.BookLoanding.book_loanding.category.dto.CategoryDTO;
import com.BookLoanding.book_loanding.category.mapper.CategoryDTOMapper;
import com.BookLoanding.book_loanding.category.model.Category;
import com.BookLoanding.book_loanding.category.repository.CategoryRepository;
import com.BookLoanding.book_loanding.category.request.CategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDTOMapper categoryDTOMapper;

    public CategoryDTO storeCategory(CategoryRequest categoryRequest) {

        Category category = new Category(categoryRequest);
        Category categorySaved = categoryRepository.save(category);
        return categoryDTOMapper.apply(categorySaved);
    }

    public List<Category> getAllCategoriesById(List<Short> ids) {

        return categoryRepository.findAllById(ids);
    }

    public List<CategoryDTO> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryDTOMapper)
                .collect(Collectors.toList());
    }

}
