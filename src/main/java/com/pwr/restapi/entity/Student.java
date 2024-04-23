package com.pwr.restapi.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name ="surname")
    private String surname;
    @Column(name = "studentNumber")
    private int studentNumber;
    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Book> books;


    public Student() {
    }

    public Student(Long id, String name, String surname, int studentNumber, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.studentNumber = studentNumber;
        this.email = email;
    }

    public Student(String name, String surname, int studentNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.studentNumber = studentNumber;
        this.email = email;
    }

    public Student(String name, String surname, int studentNumber, String email, List<Book> books) {
        this.name = name;
        this.surname = surname;
        this.studentNumber = studentNumber;
        this.email = email;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if (books.contains(book)) {
            return;
        }
        books.add(book);
    }

    public void deleteBook(Book book) {
        books.remove(book);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", studentNumber=" + studentNumber +
                ", email='" + email + '\'' +
                ", books=" + books +
                '}';
    }
}
