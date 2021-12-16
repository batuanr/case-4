package com.example.case_module_4.service.book;

import com.example.case_module_4.model.Author;
import com.example.case_module_4.model.Book;
import com.example.case_module_4.model.Category;
import com.example.case_module_4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService extends IGeneralService<Book> {
    Page<Book> findAll(Pageable pageable);
    Page<Book> findAllByNameContaining (Pageable pageable,String name);
    Page<Book> findAllByAuthor(Pageable pageable, Author author);
    Page<Book> findBookByCategory(Pageable pageable, Category category);
    Iterable<Book> findTop4Book( );
}
