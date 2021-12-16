package com.example.case_module_4.controller;

import com.example.case_module_4.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
@CrossOrigin
public class BookController {
    @Autowired
     private IBookService bookService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Book>> showBookList(){
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createNewBook(Book book){
         bookService.save(book);
         return new ResponseEntity<>(book,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id){
        bookService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBookById(@RequestBody Book book){
        bookService.save(book);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id){
        return new ResponseEntity<>(bookService.findById(id).get(),HttpStatus.OK);
    }
}
