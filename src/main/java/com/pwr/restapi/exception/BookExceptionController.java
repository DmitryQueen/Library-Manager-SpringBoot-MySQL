package com.pwr.restapi.exception;


import com.pwr.restapi.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionController {

    @ExceptionHandler(value = {BookNotFoundException.class})
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException bookNotFoundException) {

        BookException bookException = new BookException(bookNotFoundException.getMessage(), bookNotFoundException.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(bookException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BookIsBookedException.class})
    public ResponseEntity<Object> handleBookIsBookedException(BookIsBookedException bookIsBookedException) {

        BookException bookException = new BookException(bookIsBookedException.getMessage(), bookIsBookedException.getCause(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(bookException, HttpStatus.CONFLICT);
    }
}
