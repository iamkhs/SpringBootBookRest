package com.iamkhs.book.repository;

import com.iamkhs.book.entities.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository{
    Book findBookByTitle(String name);

    List<Book> findAll();

    Optional<Book> findById(int id);

    Book save(Book book);

    void delete(Book book);

    void deleteAll();
}
