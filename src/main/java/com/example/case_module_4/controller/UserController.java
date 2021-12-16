package com.example.case_module_4.controller;

import com.example.case_module_4.model.Role;
import com.example.case_module_4.model.User;
import com.example.case_module_4.service.user.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    Environment env;
    @Autowired
    IUserService userService;
    @GetMapping("/list")
    public ResponseEntity<Iterable<User>> getAll(){
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Role> create(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestPart("file") MultipartFile file, @RequestPart String user){
        if (!file.isEmpty()){
            String file1 = file.getOriginalFilename();
            try {
                User user1 = new ObjectMapper().readValue(user, User.class);
                user1.setImage(file1);
                userService.save(user1);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            String fileUpload = env.getProperty("upload.path");

            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + file1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
