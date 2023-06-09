package com.devtiro.books.booksapi.services.implementations;

import com.devtiro.books.booksapi.model.Book;
import com.devtiro.books.booksapi.model.BookEntity;
import com.devtiro.books.booksapi.repository.BookRepository;
import com.devtiro.books.booksapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// here via @service annotation, we can automatically inject the implementation of bookService interface, once bookService got injected somewhere

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    /* DI Bookrepository interface here which also brings along jpaRepository interfaces*/

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(final Book book) {
        final BookEntity bookEntity = bookToBookEntity(book);
        final BookEntity savedBookEntity = bookRepository.save(bookEntity);
        return bookEntityToBook(savedBookEntity);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        Optional<BookEntity> foundBook = bookRepository.findById(isbn);
        return foundBook.map(book->bookEntityToBook(book));
    }

    @Override
    public List<Book> getAllBooks() {
        List<BookEntity> listOfBooks = bookRepository.findAll();
        List<Book> listOfAllBooks = listOfBooks.stream().map(singleBookEntity -> bookEntityToBook(singleBookEntity))
                .collect(Collectors.toList());
        return listOfAllBooks;
    }

    @Override
    public BookEntity updateBookByIsbn(String isbnOfBookToBeUpdated) {
        Optional<BookEntity> bookEntityToBeUpdatedFound = bookRepository.findById(isbnOfBookToBeUpdated);
        if (bookEntityToBeUpdatedFound.isPresent()) {
            BookEntity bookEntityToBeUpdated = bookEntityToBeUpdatedFound.get();
            System.out.println("Value found");
           return bookEntityToBeUpdated;
        } else {
            System.out.println("Value not found");
            return null; // Or throw an exception or handle the case of book not found
        }
    }


    @Override
    public void deleteBookByIsbn(String bookIsbnToBeDeleted) {
        bookRepository.deleteById(bookIsbnToBeDeleted);
    }

    private BookEntity bookToBookEntity(Book book) {
        return BookEntity.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }

    private Book bookEntityToBook(BookEntity bookEntity) {
        return Book.builder()
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .build();
    }
}
