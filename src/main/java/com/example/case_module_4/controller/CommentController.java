package com.example.case_module_4.controller;

import com.example.case_module_4.model.Comment;
import com.example.case_module_4.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @GetMapping("/showAll")
    public ResponseEntity<Iterable<Comment>> allComment(){
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
        commentService.save(comment);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Comment> editComment(@PathVariable Long id,@RequestBody Comment comment) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment.setId(id);
        commentService.save(comment);
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id){
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentService.remove(id);
        return new ResponseEntity<>(commentOptional.get(),HttpStatus.OK);
    }
   @GetMapping("/find/{id}")
    public ResponseEntity<Comment> findCommentById(@PathVariable Long id){
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentOptional.get(),HttpStatus.OK);
   }
}
