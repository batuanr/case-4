package com.example.case_module_4.controller;

import com.example.case_module_4.model.User;
import com.example.case_module_4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    @GetMapping("/list")
    public ResponseEntity<Iterable<User>> getAll(){
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> edit(@PathVariable Long id, @RequestBody User user){
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userOptional.get().getId());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(id);
        return new ResponseEntity<>(userOptional.get(), HttpStatus.NO_CONTENT);
    }
}
