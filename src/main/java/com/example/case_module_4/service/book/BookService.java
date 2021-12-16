package com.example.case_module_4.service.book;

import com.example.case_module_4.model.Author;
import com.example.case_module_4.model.Book;
import com.example.case_module_4.model.Category;
import com.example.case_module_4.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findAllByNameContaining(Pageable pageable, String name) {
        return bookRepository.findAllByNameContaining(pageable,name);
    }

    @Override
    public Page<Book> findAllByAuthor(Pageable pageable, Author author) {
        return bookRepository.findAllByAuthor(pageable, author);
    }

    @Override
    public Page<Book> findBookByCategory(Pageable pageable, Category category) {
        return bookRepository.findAllByCategoryList(pageable, category);
    }
}
