package com.example.library.controller;

import com.example.library.entity.Category;
import com.example.library.exception.LibraryValidation;
import com.example.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    Category findById(@PathVariable int id){
        return categoryService.findById(id);
    }

    @PostMapping("/")
    Category save(@RequestBody Category category){
        return categoryService.save(category);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    Category update(@PathVariable int id, @RequestBody Category category){
        return categoryService.update(id, category);
    }
}
