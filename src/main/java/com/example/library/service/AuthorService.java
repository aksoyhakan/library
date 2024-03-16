package com.example.library.service;

import com.example.library.entity.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(int id);

    Author save(Author author);

    void delete(int id);

    Author update(int id, Author author);

}
