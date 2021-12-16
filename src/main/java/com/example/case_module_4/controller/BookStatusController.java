package com.example.case_module_4.controller;

import com.example.case_module_4.model.BookStatus;
import com.example.case_module_4.service.bookStatus.IBookStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/boookStatus")
public class BookStatusController {

    @Autowired
    private IBookStatusService bookStatusService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<BookStatus>> listBookStatus(){

        return new ResponseEntity<>(bookStatusService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookStatus> create(@RequestBody BookStatus bookStatus){
        bookStatusService.save(bookStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookStatus> find(@PathVariable Long id){
        return new ResponseEntity<>(bookStatusService.findById(id).get(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookStatus> delete(@PathVariable Long id){
        bookStatusService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<BookStatus> update(@RequestBody BookStatus bookStatus){
        bookStatusService.save(bookStatus);
        return new ResponseEntity<>(bookStatus,HttpStatus.OK);
    }

}
