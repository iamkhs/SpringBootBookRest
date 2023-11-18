package com.iamkhs.book.repository;

import com.iamkhs.book.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookByTitle(String name);
}
