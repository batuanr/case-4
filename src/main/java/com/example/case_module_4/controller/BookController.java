package com.example.case_module_4.controller;

import com.example.case_module_4.model.Book;
import com.example.case_module_4.model.Category;
import com.example.case_module_4.repository.ICategoryRepository;
import com.example.case_module_4.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
@CrossOrigin
public class BookController {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IAuthor

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

    @GetMapping("/list/category")
    public ResponseEntity<Page<Book>> findBookByCategory(Pageable pageable, @RequestParam("category") Long id){
        Category category=categoryRepository.findById(id).get();
        Page<Book> books= bookService.findBookByCategory(pageable,category);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping("/list/author")
    public ResponseEntity<Page<Book>> findBookByAuthor(Pageable pageable,@RequestParam("author") Long id){

    }
}