package com.example.library.service;

import com.example.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();
    Book findById(int id);
    Book save(Book book);
    void delete(Book book);
    Book update(int id, Book book);
    Book addCategory(int categoryId, Book book);
    Book addCategoryAuthor(int categoryId, int authorId, Book book);
}
