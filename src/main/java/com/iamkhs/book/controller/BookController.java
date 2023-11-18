package com.iamkhs.book.controller;

import com.iamkhs.book.entities.Book;
import com.iamkhs.book.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String test(){
        return "Hello World working";
    }
    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks(){
        System.err.println("inside controller");
        var bookList = bookService.getBooks();
        if (bookList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(bookList));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        var book = bookService.getBookById(id);
        if (book.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(book);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book){
        return this.bookService.addBook(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookById(@PathVariable int id){
        this.bookService.deleteById(id);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book newBok){
        return this.bookService.updateBookById(id, newBok);
    }

    @GetMapping("/books/title/{name}")
    public Book getBookByTitle(@PathVariable String name){
        return bookService.getBookByName(name);
    }


    @DeleteMapping("/books/all")
    public void deleteAllBooks(){
        this.bookService.deleteAll();
    }

}
