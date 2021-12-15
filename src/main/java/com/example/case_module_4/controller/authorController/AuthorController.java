package com.example.case_module_4.controller.authorController;

import com.example.case_module_4.model.Author;
import com.example.case_module_4.service.authorService.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private IAuthorService authorService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Author>> showAllAuthor() {
        List<Author> authorList = (List<Author>) authorService.findAll();
        if (authorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authorList,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Author> addNewAuthor(@RequestBody Author author) {
        authorService.save(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        if (!author.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.remove(id);
        return new ResponseEntity<>(author.get(),HttpStatus.OK);
    }
}
