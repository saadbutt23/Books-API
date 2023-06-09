package com.devtiro.books.booksapi.repository;

import com.devtiro.books.booksapi.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookRepository extends JpaRepository<BookEntity,String> {
}
