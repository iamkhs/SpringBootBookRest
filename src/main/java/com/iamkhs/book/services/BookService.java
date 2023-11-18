package com.iamkhs.book.services;

import com.iamkhs.book.entities.Book;
import com.iamkhs.book.repository.BookRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // get all books
    @Cacheable(value = "books")
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    // get single book by id
    public Optional<Book> getBookById(int id){
        return bookRepository.findById(id);
    }

    // add new book
    public Book addBook(Book book){
        return this.bookRepository.save(book);
    }

    // delete book by id
    public void deleteById(int id){
        var book = getBookById(id);
        if (book.isPresent()){
//            bookList.remove(book);
            this.bookRepository.delete(book.get());
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    // update book by id
    public Book updateBookById(int id, Book newBook){
        var book = getBookById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        return bookRepository.save(book);
    }

    // get book by name
    public Book getBookByName(String name){
        var book = bookRepository.findBookByTitle(name);
        if (book == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return book;
    }

    public void deleteAll(){
        this.bookRepository.deleteAll();
    }

}
