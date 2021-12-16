package com.example.case_module_4.controller;

import com.example.case_module_4.model.Category;
import com.example.case_module_4.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/showAll")
    public ResponseEntity<Iterable<Category>> allCategory() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ModelAndView getAllCategoryPage() {
        ModelAndView modelAndView = new ModelAndView("/list");
        return modelAndView;
    }

    @PostMapping("/create")
    public ResponseEntity<Category> createNewCategory(@RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<Category> updateCategoryById( @RequestBody Category category) {
       categoryService.save(category);
       return new ResponseEntity<>(HttpStatus.OK);

    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return  new ResponseEntity<>(categoryOptional.get(),HttpStatus.OK);
    }

}
