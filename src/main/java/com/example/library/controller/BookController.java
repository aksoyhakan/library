package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.exception.LibraryValidation;
import com.example.library.mapping.BookReturn;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import com.example.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService, AuthorService authorService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    List<BookReturn> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    BookReturn findById(@PathVariable int id){
        return bookService.findById(id);
    }

    @PostMapping("/")
    BookReturn save(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        bookService.delete(id);
    }

    @PutMapping("/{id}")
    BookReturn update(@PathVariable int id, @RequestBody Book book){
        return bookService.update(id,book);
    }

    @PostMapping("/{categoryId}")
    BookReturn addCategory(@PathVariable int categoryId, @RequestBody Book book){
        return bookService.addCategory(categoryId,book);
    }

    @PostMapping("/{categoryId}/{authorId}")
    BookReturn addCategory(@PathVariable int categoryId, @PathVariable int authorId, @RequestBody Book book){
        return bookService.addCategoryAuthor(categoryId,authorId,book);
    }
}
