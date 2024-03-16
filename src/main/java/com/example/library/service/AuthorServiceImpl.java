package com.example.library.service;

import com.example.library.LibraryApplication;
import com.example.library.entity.Author;
import com.example.library.exception.LibraryValidation;
import com.example.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(int id) {
        LibraryValidation.existIdAuthorCheck(authorRepository.findAll(),id);
        return authorRepository.findById(id).orElseThrow();
    }

    @Override
    public Author save(Author author) {
        LibraryValidation.checkAuthorPayload(author);
        LibraryValidation.existAuthorNameCheck(authorRepository.findAll(),author.getFirstName(), author.getLastName());
        return authorRepository.save(author);
    }

    @Override
    public void delete(int id) {
        Author deletingAuthor=authorRepository.findById(id).orElseThrow();
        authorRepository.delete(deletingAuthor);
    }

    @Override
    public Author update(int id, Author author) {
        LibraryValidation.existIdAuthorCheck(authorRepository.findAll(),id);
        LibraryValidation.checkAuthorPayload(author);
        return authorRepository.findById(id).map(entity -> {
            entity.setFirstName(author.getFirstName());
            entity.setLastName(author.getLastName());
            return authorRepository.save(entity);
        }).orElseThrow();
    }

}
