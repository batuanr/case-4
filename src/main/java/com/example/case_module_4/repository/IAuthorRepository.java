package com.example.case_module_4.repository;

import com.example.case_module_4.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends JpaRepository<Author,Long> {
Iterable<Author> findAuthorByAuthorNameContaining(String authorName);
}
