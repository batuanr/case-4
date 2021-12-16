package com.example.case_module_4.controller;

import com.example.case_module_4.model.BookStatus;
import com.example.case_module_4.model.Role;
import com.example.case_module_4.service.bookStatus.IBookStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/bookStatus")
public class BookStatusController {

    @Autowired
    private IBookStatusService bookStatusService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<BookStatus>> getAll(){
        Iterable<BookStatus> bookStatuses = bookStatusService.findAll();
        return new ResponseEntity<>(bookStatuses, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<BookStatus> create(@RequestBody BookStatus bookStatus){
        bookStatusService.save(bookStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<BookStatus> findOne(@PathVariable Long id){
        Optional<BookStatus> bookStatusOptional = bookStatusService.findById(id);
        if (!bookStatusOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookStatusOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/edit")
    public ResponseEntity<BookStatus> edit(@RequestBody BookStatus bookStatus){
        Optional<BookStatus> bookStatusOptional = bookStatusService.findById(bookStatus.getId());
        if (!bookStatusOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookStatusService.save(bookStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookStatus> delete(@PathVariable Long id){
        Optional<BookStatus> bookStatusOptional = bookStatusService.findById(id);
        if (!bookStatusOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookStatusService.remove(id);
        return new ResponseEntity<>(bookStatusOptional.get(), HttpStatus.NO_CONTENT);
    }

}
