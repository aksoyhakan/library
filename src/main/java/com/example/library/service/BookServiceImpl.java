package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import com.example.library.exception.LibraryValidation;
import com.example.library.mapping.BookReturn;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;
import com.example.library.utils.BookUtil;
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
    public List<BookReturn> findAll() {
        return bookRepository.findAll().stream().map(book->BookUtil.constructBookResponse(book)).toList();
    }

    @Override
    public BookReturn findById(int id) {
        LibraryValidation.existIdBookCheck(bookRepository.findAll(),id);
        return BookUtil.constructBookResponse(bookRepository.findById(id).orElseThrow()) ;
    }

    @Override
    public BookReturn save(Book book) {
        LibraryValidation.checkBookPayload(book);
        LibraryValidation.existBookNameCheck(bookRepository.findAll(),book.getName());
        return BookUtil.constructBookResponse(bookRepository.save(book));
    }

    @Override
    public void delete(int id) {
        LibraryValidation.existIdBookCheck(bookRepository.findAll(),id);
        Book deletingBook=bookRepository.findById(id).orElseThrow();
        bookRepository.delete(deletingBook);
    }

    @Override
    public BookReturn update(int id, Book book) {
        LibraryValidation.existIdBookCheck(bookRepository.findAll(),id);
        LibraryValidation.checkBookPayload(book);
        return BookUtil.constructBookResponse(bookRepository.findById(id).map(item->{
            item.setName(book.getName());
            item.setAuthor(book.getAuthor());
            item.setCategory(book.getCategory());
            return bookRepository.save(item);
        }).orElseThrow());
    }

    @Override
    public BookReturn addCategory(int categoryId, Book book) {
        LibraryValidation.existIdCategoryCheck(categoryRepository.findAll(),categoryId);
        LibraryValidation.checkBookPayload(book);
        Category searchedCategory=categoryRepository.findById(categoryId).orElseThrow();
        List<Book> books=searchedCategory.getBook();
        books.add(book);
        searchedCategory.setBook(books);
        book.setCategory(searchedCategory);
        return BookUtil.constructBookResponse(bookRepository.save(book)) ;
    }

    @Override
    public BookReturn addCategoryAuthor(int categoryId, int authorId, Book book) {
        LibraryValidation.existIdCategoryCheck(categoryRepository.findAll(),categoryId);
        LibraryValidation.existIdAuthorCheck(authorRepository.findAll(),authorId);
        LibraryValidation.checkBookPayload(book);
        Category searchedCategory=categoryRepository.findById(categoryId).orElseThrow();
        Author searchedAuthor=authorRepository.findById(authorId).orElseThrow();
        List<Book> booksCategory=searchedCategory.getBook();
        booksCategory.add(book);
        List<Book> booksAuthor=searchedAuthor.getBook();
        booksAuthor.add(book);
        book.setCategory(searchedCategory);
        book.setAuthor(searchedAuthor);
        return BookUtil.constructBookResponse(bookRepository.save(book)) ;
    }
}
