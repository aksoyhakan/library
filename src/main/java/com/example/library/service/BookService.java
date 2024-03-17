package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.mapping.BookReturn;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookReturn> findAll();
    BookReturn findById(int id);
    BookReturn save(Book book);
    void delete(int id);
    BookReturn update(int id, Book book);
    BookReturn addCategory(int categoryId, Book book);
    BookReturn addCategoryAuthor(int categoryId, int authorId, Book book);
}
