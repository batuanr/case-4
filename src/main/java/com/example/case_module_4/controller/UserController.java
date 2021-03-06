package com.example.case_module_4.controller;

import com.example.case_module_4.model.Role;
import com.example.case_module_4.model.User;
import com.example.case_module_4.service.role.IRoleService;
import com.example.case_module_4.service.user.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    Environment env;
    @Autowired
    IRoleService roleService;
    @Autowired
    IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public ResponseEntity<Iterable<User>> getAll(){
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody User user){
        Set<Role> roleSet = new HashSet<>();
        Role role = roleService.findByName("ROLE_USER");
        roleSet.add(role);
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userOptional.get().getId());
        String pw = userOptional.get().getPassword();
        user.setPassword(pw);
        user.setUsername(userOptional.get().getUsername());
        user.setRoles(userOptional.get().getRoles());
        user.setImage(userOptional.get().getImage());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/editPhoto/{id}")
    public ResponseEntity<User> editPhoto(@PathVariable Long id, @RequestPart("file") MultipartFile file){
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!file.isEmpty()){
            String file1 = file.getOriginalFilename();
            String fileUpload = env.getProperty("upload.path");
            userOptional.get().setImage(file1);
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + file1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userService.save(userOptional.get());
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    @GetMapping("/findManager")
    public ResponseEntity<Page<User>> findUserByRole(@PageableDefault(value = 10)Pageable pageable){
        Optional<Role> roleOptional=roleService.findById(Long.valueOf(2));
        Page<User> users=userService.findAllByRoles(pageable,roleOptional.get());
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
