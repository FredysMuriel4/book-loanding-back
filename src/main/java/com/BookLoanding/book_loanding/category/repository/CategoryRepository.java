package com.BookLoanding.book_loanding.category.repository;

import com.BookLoanding.book_loanding.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Short> {
}
