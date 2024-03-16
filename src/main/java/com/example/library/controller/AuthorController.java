package com.example.library.controller;


import com.example.library.entity.Author;
import com.example.library.exception.LibraryValidation;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(@Qualifier("authorServiceImpl") AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    List<Author> findAll(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    Author findById(@PathVariable int id){
        return authorService.findById(id);
    }

    @PostMapping("/")
    Author save(@RequestBody Author author){
        return authorService.save(author);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        authorService.delete(id);
    }

    @PutMapping("/{id}")
    Author update(@PathVariable int id, @RequestBody Author author){
        return authorService.update(id, author);
    }

}
