package com.example.case_module_4.service.authorService;

import com.example.case_module_4.model.Author;
import com.example.case_module_4.service.IGeneralService;

import java.util.Optional;

public interface IAuthorService extends IGeneralService<Author> {
    @Override
    Iterable<Author> findAll();

    @Override
    Optional<Author> findById(Long id);

    @Override
    void save(Author author);

    @Override
    void remove(Long id);

    Iterable<Author> findAuthorByAuthorName(String authorName);
}
