package com.devtiro.books.booksapi.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;
/*
1. Using this class so that in case you need to work with some additional infos rather than just three fields, and those additional infos must be not saved in DB.
Hence, giving us more extensibility
2. We use this class now for service layer, as BookEntity is just there to store and retrieve data from and to the DB (infos only required to be stored and retrieved from DB)
*/

@Builder
public class Book {
    private String isbn;
    private String title;
    private String author;

    public Book(String isbn, String title,String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, isbn, title);
    }
}
