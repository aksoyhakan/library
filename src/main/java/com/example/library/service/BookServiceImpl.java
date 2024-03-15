package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Book update(int id, Book book) {
        return bookRepository.findById(id).map(item->{
            item.setName(book.getName());
            item.setAuthor(book.getAuthor());
            item.setCategory(book.getCategory());
            return bookRepository.save(item);
        }).orElseThrow();
    }

    @Override
    public Book addCategory(int categoryId, Book book) {
        Category searchedCategory=categoryRepository.findById(categoryId).orElseThrow();
        List<Book> books=searchedCategory.getBook();
        books.add(book);
        searchedCategory.setBook(books);
        book.setCategory(searchedCategory);
        return bookRepository.save(book);
    }

    @Override
    public Book addCategoryAuthor(int categoryId, int authorId, Book book) {
        Category searchedCategory=categoryRepository.findById(categoryId).orElseThrow();
        Author searchedAuthor=authorRepository.findById(authorId).orElseThrow();
        List<Book> booksCategory=searchedCategory.getBook();
        booksCategory.add(book);
        List<Book> booksAuthor=searchedAuthor.getBook();
        booksAuthor.add(book);
        book.setCategory(searchedCategory);
        book.setAuthor(searchedAuthor);
        return bookRepository.save(book);
    }
}
