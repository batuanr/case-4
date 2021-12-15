package com.example.case_module_4.controller;

import com.example.case_module_4.model.Role;
import com.example.case_module_4.model.User;
import com.example.case_module_4.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleService roleService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Role>> getAll(){
        Iterable<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Role> create(@RequestBody Role role){
        roleService.save(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Role> findOne(@PathVariable Long id){
        Optional<Role> roleOptional = roleService.findById(id);
        if (!roleOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roleOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> edit(@PathVariable Long id, @RequestBody Role role){
        Optional<Role> roleOptional = roleService.findById(id);
        if (!roleOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        role.setId(roleOptional.get().getId());
        roleService.save(role);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Role> delete(@PathVariable Long id){
        Optional<Role> roleOptional = roleService.findById(id);
        if (!roleOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roleService.remove(id);
        return new ResponseEntity<>(roleOptional.get(), HttpStatus.NO_CONTENT);
    }
}
