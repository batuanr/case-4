package com.example.case_module_4.service.comment;

import com.example.case_module_4.model.Book;
import com.example.case_module_4.model.Comment;
import com.example.case_module_4.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICommentService extends IGeneralService<Comment> {
    Iterable<Comment> findAllByBook(Long id);
    Page<Comment> findAll(Pageable pageable);
}
