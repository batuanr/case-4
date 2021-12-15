package com.example.case_module_4.service.bookStatus;

import com.example.case_module_4.model.BookStatus;
import com.example.case_module_4.repository.IBookStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BookStatusService implements IBookStatusService{
    @Autowired
    private IBookStatusRepository bookStatusRepository;

    @Override
    public Iterable<BookStatus> findAll() {
        return bookStatusRepository.findAll();
    }

    @Override
    public Optional<BookStatus> findById(Long id) {
        return bookStatusRepository.findById(id);
    }

    @Override
    public void save(BookStatus bookStatus) {
        bookStatusRepository.save(bookStatus);
    }

    @Override
    public void remove(Long id) {
        bookStatusRepository.deleteById(id);
    }
}
