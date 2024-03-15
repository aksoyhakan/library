package com.example.library.exception;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class LibraryValidation {

    public static void checkAuthorPayload(Author author){
        if(author.getFirstName().isEmpty()){
            throw new LibraryException("First name is not valid", HttpStatus.BAD_REQUEST);
        }
        if(author.getLastName().isEmpty()){
            throw new LibraryException("Last name is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkCategoryPayload(Category category){
        if(category.getName().isEmpty()) throw new LibraryException("Category name is not valid", HttpStatus.BAD_REQUEST);
    }

    public static void checkBookPayload(Book book){
        if(book.getName().isEmpty()) throw new LibraryException("Book name is not valid",HttpStatus.BAD_REQUEST);
    }

    public static void existIdBookCheck(List<Book> books, int id){
        Optional<Book> searchBook=books.stream().filter(item->item.getId()==id).findFirst();
        if(searchBook.isEmpty()) throw new LibraryException("Book id is not found",HttpStatus.NOT_FOUND);
    }

    public static void existIdCategoryCheck(List<Category> categories, int id){
        Optional<Category> searchCategory=categories.stream().filter(item->item.getId()==id).findFirst();
        if(searchCategory.isEmpty()) throw new LibraryException("Category id is not found",HttpStatus.NOT_FOUND);
    }

    public static void existIdAuthorCheck(List<Author> authors, int id){
        Optional<Author> searchAuthor=authors.stream().filter(item->item.getId()==id).findFirst();
        if(searchAuthor.isEmpty()) throw new LibraryException("Author id is not found",HttpStatus.NOT_FOUND);
    }

    public static void existBookNameCheck(List<Book> books, String name){
        Optional<Book> searchBook=books.stream().filter(item->item.getName().equals(name)).findFirst();
        if(searchBook.isPresent()) throw new LibraryException("Bookname is already logged", HttpStatus.BAD_REQUEST);
    }

    public static void existAuthorNameCheck(List<Author> authors, String firstName, String lastName){
        Optional<Author> searchAuthor=authors.stream().filter(item->item.getFirstName().equals(firstName)&&item.getLastName().equals(lastName)).findFirst();
        if(searchAuthor.isPresent()) throw new LibraryException("Author is already logged", HttpStatus.BAD_REQUEST);
    }

    public static void existCategoryNameCheck(List<Category> categories,String name){
        Optional<Category> searchCategory=categories.stream().filter(item->item.getName().equals(name)).findFirst();
        if(searchCategory.isPresent()) throw new LibraryException("Category is already logged",HttpStatus.BAD_REQUEST);
    }

}
