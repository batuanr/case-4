package com.example.case_module_4.repository;

import com.example.case_module_4.model.Book;
import com.example.case_module_4.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Long> {
   Iterable<Comment> findAllByBook(Book book);
   Page<Comment> findAllByBookOrderByIdDesc(Book book,Pageable pageable);
}
