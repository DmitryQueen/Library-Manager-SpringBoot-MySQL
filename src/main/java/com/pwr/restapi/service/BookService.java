package com.pwr.restapi.service;


import com.pwr.restapi.entity.Book;
import com.pwr.restapi.entity.Student;
import com.pwr.restapi.exception.BookNotFoundException;
import com.pwr.restapi.exception.StudentIsPresentException;
import com.pwr.restapi.exception.StudentNotFoundException;
import com.pwr.restapi.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public String addNewBook(Book book) {
        Optional<Book> existingBook = bookRepository.findBookByTitle(book.getTitle());

        if (existingBook.isPresent()) {
            throw new StudentIsPresentException("Book with title " + book.getTitle() + " is already exists");
        }
        bookRepository.save(book);
        return "Book was succesfully added";
    }

    @Transactional
    public String updateBookById(Long bookId, Book newBook) {
        Book existingBook = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with id = " + bookId + " was not found"));

        String title = newBook.getTitle();
        if (title != null && title.length() > 0 && !Objects.equals(title, existingBook.getTitle())) {
            existingBook.setTitle(title);
        }

        String author = newBook.getAuthor();
        if (author != null && author.length() > 0 && !Objects.equals(author, existingBook.getAuthor())) {
            existingBook.setAuthor(author);
        }

        return "Book with id " + bookId + " was succesfully updated";
    }

    public String deleteBook(Long bookId) {

        if (bookRepository.findById(bookId).isEmpty()) {
            throw new BookNotFoundException("Book with id = " + bookId + " was not found");
        }
        bookRepository.deleteById(bookId);
        return "Book with id = " + bookId + " was succesfully deleted";
    }
}
