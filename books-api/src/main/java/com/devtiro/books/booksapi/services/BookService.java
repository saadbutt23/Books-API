package com.devtiro.books.booksapi.services;

import com.devtiro.books.booksapi.model.Book;
import com.devtiro.books.booksapi.model.BookEntity;

import java.util.List;
import java.util.Optional;

// Basically this interface will be injected in controller
// as its implementation class defined as @Service, hence its implementation methods will be injected automatically once interface gets injected. COOL!!!
public interface BookService {
    Book createBook(Book book);
    Optional<Book> findByIsbn(String isbn);
    List<Book> getAllBooks();

    BookEntity updateBookByIsbn(String isbnOfBookToBeUpdated);

    void deleteBookByIsbn(String bookIsbnToBeDeleted);
}
