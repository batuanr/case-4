package com.example.case_module_4.service.comment;

import com.example.case_module_4.model.Book;
import com.example.case_module_4.model.Comment;
import com.example.case_module_4.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService{
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> findAllByBook(Book book) {
        return commentRepository.findAllByBook(book);
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Page<Comment> findAllByBook(Book book, Pageable pageable) {
        return commentRepository.findAllByBookOrderByIdDesc(book,pageable);
    }

//    @Override
//    public Page<Comment> findAllByBookId(Long id,Pageable pageable) {
//        return commentRepository.findAllByBook(id,pageable);
//    }
}
