package com.example.case_module_4.controller;

import com.example.case_module_4.model.BookStatus;
import com.example.case_module_4.model.Cart;
import com.example.case_module_4.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Cart>> getAll(){
        Iterable<Cart> carts = cartService.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Cart> create(@RequestBody Cart cart){
        cartService.save(cart);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<Cart> findOne(@PathVariable Long id){
        Optional<Cart> cartOptional = cartService.findById(id);
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/edit")
    public ResponseEntity<Cart> edit(@RequestBody Cart cart){
        Optional<Cart> cartOptional = cartService.findById(cart.getId());
        if (!cartOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartService.save(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Cart> delete(@PathVariable Long id){
        Optional<Cart> cartOptional = cartService.findById(id);
        if (!cartOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartService.remove(id);
        return new ResponseEntity<>(cartOptional.get(), HttpStatus.NO_CONTENT);
    }
}
