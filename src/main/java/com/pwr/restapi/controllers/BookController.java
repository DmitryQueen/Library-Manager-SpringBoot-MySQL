package com.pwr.restapi.controllers;


import com.pwr.restapi.entity.Book;
import com.pwr.restapi.entity.Student;
import com.pwr.restapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping(path = "/")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping(path = "/")
    public ResponseEntity<String> registerNewBook(@RequestBody Book book) {
        String result = bookService.addNewBook(book);
        return ResponseEntity.ok(result);
    }


    @PutMapping(path = "/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        String result = bookService.updateBookById(bookId, book);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable("bookId") Long bookId) {
        Book book = bookService.getBookById(bookId);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping(path = "/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") Long bookId) {
        String result = bookService.deleteBook(bookId);
        return ResponseEntity.ok(result);
    }
}
