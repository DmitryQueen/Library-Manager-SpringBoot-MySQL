package com.pwr.restapi.service;


import com.pwr.restapi.entity.Book;
import com.pwr.restapi.entity.Student;
import com.pwr.restapi.exception.*;
import com.pwr.restapi.repository.BookRepository;
import com.pwr.restapi.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private BookRepository bookRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository, BookRepository bookRepository) {
        this.studentRepository = studentRepository;
        this.bookRepository =  bookRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public String addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new StudentIsPresentException("Student with email = " + student.getEmail() + " is already exists");
        }
        studentRepository.save(student);
        return "Student was succesfully added";
    }

    @Transactional
    public String updateStudentById(Long studentId, Student newStudentInfo) {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id = " + studentId + " was not found"));

        String name = newStudentInfo.getName();
        if (name != null && name.length() > 0 && !Objects.equals(name, existingStudent.getName())) {
            existingStudent.setName(name);
        }

        String surname = newStudentInfo.getSurname();
        if (surname != null && surname.length() > 0 && !Objects.equals(surname, existingStudent.getSurname())) {
            existingStudent.setSurname(surname);
        }

        int studentNumber = newStudentInfo.getStudentNumber();
        if (studentNumber != 0 && studentNumber != existingStudent.getStudentNumber()) {
            existingStudent.setStudentNumber(studentNumber);
        }

        String email = newStudentInfo.getEmail();
        if (email != null && email.length() > 0 && !Objects.equals(email, existingStudent.getEmail())) {
            Optional<Student> student = studentRepository.findStudentByEmail(email);
            if (student.isEmpty()) {
                existingStudent.setEmail(email);
            }
            else {
                throw new StudentIsPresentException("Student with email = " + email + " is already exists");
            }
        }

        return "Student with id " + studentId + " was succesfully updated";
    }


    @Transactional
    public String deleteStudent(Long studentId) {

        Optional<Student> studentOpt = studentRepository.findById(studentId);

        if (studentOpt.isEmpty()) {
            throw new StudentNotFoundException("Student with id = " + studentId + " was not found");
        }

        Student student = studentOpt.get();

        for (Book book : student.getBooks()) {
            book.setStudent(null);
            book.setBooked(false);
        }

        studentRepository.deleteById(studentId);
        return "Student with id = " + studentId + " was succesfully deleted";
    }


    @Transactional
    public String addBookToStudent(Long studentId, Long bookId) {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id = " + studentId + " was not found"));
        Book existingBook = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with id = " + bookId + " was not found"));

        if (existingBook.isBooked()) {
            throw new BookIsBookedException("Book with id = " + bookId + " is booked ");
        }

        existingStudent.addBook(existingBook);

        return "Book with id = " + bookId + " was added to student with id = "  + studentId;
    }

    public Student getStudentById(Long studentId) {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id = " + studentId + " was not found"));
        return existingStudent;
    }

    @Transactional
    public String deleteBookFromStudent(Long studentId, Long bookId) {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id = " + studentId + " was not found"));
        Book existingBook = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with id = " + bookId + " was not found"));
        if (!existingStudent.getBooks().contains(existingBook)) {
            throw new StudentBookNotFoundException("Student with id = " + studentId + " don't have the book with id = " + bookId);
        }

        existingStudent.deleteBook(existingBook);

        return "Book with id = " + bookId + " was successfully deleted from a student with id = " + studentId;
    }
}
