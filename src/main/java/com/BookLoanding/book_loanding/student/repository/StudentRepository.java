package com.BookLoanding.book_loanding.student.repository;

import com.BookLoanding.book_loanding.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Short> {
}
