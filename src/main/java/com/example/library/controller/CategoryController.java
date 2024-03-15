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
        LibraryValidation.existIdCategoryCheck(categoryService.findAll(),id);
        return categoryService.findById(id);
    }

    @PostMapping("/")
    Category save(@RequestBody Category category){
        LibraryValidation.checkCategoryPayload(category);
        LibraryValidation.existCategoryNameCheck(categoryService.findAll(),category.getName());
        return categoryService.save(category);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        LibraryValidation.existIdCategoryCheck(categoryService.findAll(),id);
        Category deletingCategory=categoryService.findById(id);
        categoryService.delete(deletingCategory);
    }

    @PutMapping("/{id}")
    Category update(@PathVariable int id, @RequestBody Category category){
        LibraryValidation.existIdCategoryCheck(categoryService.findAll(),id);
        LibraryValidation.checkCategoryPayload(category);
        return categoryService.update(id, category);
    }
}
