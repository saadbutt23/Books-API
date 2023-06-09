package com.devtiro.books.booksapi.controllers;

import com.devtiro.books.booksapi.model.Book;
import com.devtiro.books.booksapi.model.BookEntity;
import com.devtiro.books.booksapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Updating an already existing book's isbn
    @PostMapping(path = "/create")
    public ResponseEntity<Book> createBook(@RequestBody final Book bookEntry) {
        final Book savedBook = bookService.createBook(bookEntry);
        final ResponseEntity<Book> res = new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
        return res;
    }

    @GetMapping(path = "/all")
    public List<Book> getBookAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return allBooks;
    }

    @GetMapping(path = "/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        // Optional if the book exits or if it does not exist.
        Optional<Book> foundBook = bookService.findByIsbn(isbn);
        return foundBook.map(book-> new ResponseEntity<Book>(book,HttpStatus.OK))
                .orElse(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path="/update/{isbn}")
    public ResponseEntity<Book> updateBookByIsbn(@PathVariable String isbn, @RequestBody final Book requiredBook)  {
        BookEntity book = bookService.updateBookByIsbn(isbn);
        if(book!=null) {
            bookService.createBook(requiredBook);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @DeleteMapping(path="/delete/{isbn}")
    public ResponseEntity<Book> deleteBook(@PathVariable final String isbn) {
            bookService.deleteBookByIsbn(isbn);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
