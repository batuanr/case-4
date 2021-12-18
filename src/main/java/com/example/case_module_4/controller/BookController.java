package com.example.case_module_4.controller;

import com.example.case_module_4.model.Author;
import com.example.case_module_4.model.Book;
import com.example.case_module_4.model.Category;
import com.example.case_module_4.repository.IAuthorRepository;
import com.example.case_module_4.repository.ICategoryRepository;
import com.example.case_module_4.service.authorService.IAuthorService;
import com.example.case_module_4.service.book.IBookService;
import com.example.case_module_4.service.category.ICategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {
    @Autowired
    Environment env;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private IBookService bookService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Book>> showBookList() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }
    @GetMapping
    private ResponseEntity<Page<Book>> bookList(@PageableDefault(value = 12) Pageable pageable){
        Page<Book> books = bookService.findAll(pageable);
        return  new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createNewBook(@RequestPart("file") MultipartFile file, @RequestPart("book") String book) {
        if(!file.isEmpty()){
            String file1= file.getOriginalFilename();
            Book book1= null;
            try {
                book1 = new ObjectMapper().readValue(book, Book.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            book1.setImage(file1);
            bookService.save(book1);
            String fileUpload=env.getProperty("upload.path");
            try {
                FileCopyUtils.copy(file.getBytes(),new File(fileUpload+file1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id) {
        bookService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBookById(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/list/category")
    public ResponseEntity<Page<Book>> findBookByCategory(@PageableDefault(value = 12) Pageable pageable, @RequestBody Category category) {
//        Category category=categoryService.findById(id).get();
        Page<Book> books = bookService.findBookByCategory(pageable, category);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/list/author")
    public ResponseEntity<Page<Book>> findBookByAuthor(@PageableDefault(value = 12) Pageable pageable, @RequestBody Author author) {
//        Author author = authorService.findById(id).get();
        Page<Book> bookList = bookService.findAllByAuthor(pageable, author);
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/findbyname")
    public ResponseEntity<Page<Book>> findBookByName(@PageableDefault(value = 12) Pageable pageable, @RequestParam("book") String bookName) {
        Page<Book> listBook = bookService.findAllByNameContaining(pageable, bookName);
        return new ResponseEntity<>(listBook, HttpStatus.OK);
    }

    @GetMapping("/top4book")
    public ResponseEntity<Iterable<Book>> findTop4Book(){
        return new ResponseEntity<>(bookService.findTop4Book(),HttpStatus.OK);
    }

    @GetMapping("/bestBook")
    public ResponseEntity<Book> bestBook(){
        return new ResponseEntity<>(bookService.findTopBook().get(),HttpStatus.OK);
    }
}