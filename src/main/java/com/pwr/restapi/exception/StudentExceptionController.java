package com.pwr.restapi.exception;


import com.pwr.restapi.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class StudentExceptionController {


    @ExceptionHandler(value = {StudentNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException studentNotFoundException) {

        StudentException studentException = new StudentException(studentNotFoundException.getMessage(), studentNotFoundException.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(studentException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {StudentIsPresentException.class})
    public ResponseEntity<Object> handleStudentIsPresentException(StudentIsPresentException studentIsPresentException) {
        StudentException studentException = new StudentException(studentIsPresentException.getMessage(), studentIsPresentException.getCause(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(studentException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {StudentBookNotFoundException.class})
    public ResponseEntity<Object> handleStudentBookNotFoundException(StudentBookNotFoundException studentBookNotFoundException) {

        StudentException studentException = new StudentException(studentBookNotFoundException.getMessage(), studentBookNotFoundException.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(studentException, HttpStatus.NOT_FOUND);
    }


}
