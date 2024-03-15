package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.exception.LibraryValidation;
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
    private CategoryService categoryService;

    private AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService, AuthorService authorService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @GetMapping("/")
    List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/id")
    Book findById(int id){
        LibraryValidation.existIdBookCheck(bookService.findAll(),id);
        return bookService.findById(id);
    }

    @PostMapping("/")
    Book save(@RequestBody Book book){
        LibraryValidation.checkBookPayload(book);
        LibraryValidation.existBookNameCheck(bookService.findAll(),book.getName());
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        LibraryValidation.existIdBookCheck(bookService.findAll(),id);
        Book deletingBook=bookService.findById(id);
        bookService.delete(deletingBook);
    }

    @PutMapping("/{id}")
    Book update(@PathVariable int id, @RequestBody Book book){
        LibraryValidation.existIdBookCheck(bookService.findAll(),id);
        LibraryValidation.checkBookPayload(book);
        return bookService.update(id,book);
    }

    @PostMapping("/{categoryId}")
    Book addCategory(@PathVariable int categoryId, @RequestBody Book book){
        LibraryValidation.existIdCategoryCheck(categoryService.findAll(),categoryId);
        LibraryValidation.checkBookPayload(book);
        return bookService.addCategory(categoryId,book);
    }

    @PostMapping("/{categoryId}/{authorId}")
    Book addCategory(@PathVariable int categoryId, @PathVariable int authorId, @RequestBody Book book){
        LibraryValidation.existIdCategoryCheck(categoryService.findAll(),categoryId);
        LibraryValidation.existIdAuthorCheck(authorService.findAll(),authorId);
        LibraryValidation.checkBookPayload(book);
        return bookService.addCategoryAuthor(categoryId,authorId,book);
    }
}
