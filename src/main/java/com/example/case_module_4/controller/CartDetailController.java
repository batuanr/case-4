package com.example.case_module_4.controller;

import com.example.case_module_4.model.BookStatus;
import com.example.case_module_4.model.Cart;
import com.example.case_module_4.model.CartDetail;
import com.example.case_module_4.service.cartDetail.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/cartDetail")
public class CartDetailController {

    @Autowired
    private ICartDetailService cartDetailService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<CartDetail>> getAll(){
        Iterable<CartDetail> cartDetails = cartDetailService.findAll();
        return new ResponseEntity<>(cartDetails, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<BookStatus> create(@RequestBody CartDetail bookStatus){
        cartDetailService.save(bookStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/findOne/{id}")
    public ResponseEntity<CartDetail> findOne(@PathVariable Long id){
        Optional<CartDetail> cartDetailOptional = cartDetailService.findById(id);
        if (!cartDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartDetailOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/edit")
    public ResponseEntity<CartDetail> edit(@RequestBody CartDetail cartDetail){
        Optional<CartDetail> bookStatusOptional = cartDetailService.findById(cartDetail.getId());
        if (!bookStatusOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartDetailService.save(cartDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CartDetail> delete(@PathVariable Long id){
        Optional<CartDetail> cartDetailOptional = cartDetailService.findById(id);
        if (!cartDetailOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartDetailService.remove(id);
        return new ResponseEntity<>(cartDetailOptional.get(), HttpStatus.NO_CONTENT);
    }
}