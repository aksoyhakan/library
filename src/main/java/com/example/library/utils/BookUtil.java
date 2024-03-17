package com.example.library.utils;

import com.example.library.entity.Book;
import com.example.library.mapping.AuthorReturn;
import com.example.library.mapping.BookReturn;
import com.example.library.mapping.CategoryReturn;

public class BookUtil {
    public static BookReturn constructBookResponse(Book book){
        return new BookReturn(book.getId(), book.getName(), new AuthorReturn(book.getAuthor()),new CategoryReturn(book.getCategory().getName()));

    }
}
