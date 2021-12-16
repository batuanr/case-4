package com.example.case_module_4.repository;

import com.example.case_module_4.model.Author;
import com.example.case_module_4.model.Book;
import com.example.case_module_4.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
   Page<Book> findAllByNameContaining(Pageable pageable,String name);
   Page<Book> findAllByAuthor(Pageable pageable, Author author);
   Page<Book> FindBookByCategory(Pageable pageable,Category category);
}
