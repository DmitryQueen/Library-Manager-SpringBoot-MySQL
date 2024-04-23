package com.pwr.restapi.controllers;


import com.pwr.restapi.entity.Student;
import com.pwr.restapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping(path = "/")
    public ResponseEntity<String> registerNewStudent(@RequestBody Student student) {
        String result = studentService.addNewStudent(student);
        return ResponseEntity.ok(result);
    }

    @PutMapping(path = "/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student student) {
        String result = studentService.updateStudentById(studentId, student);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
            String result = studentService.deleteStudent(studentId);
            return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/{studentId}/books/{bookId}")
    public ResponseEntity<String> addBookToStudent(@PathVariable Long studentId, @PathVariable Long bookId) {
        String result = studentService.addBookToStudent(studentId, bookId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/{studentId}/books/{bookId}")
    public ResponseEntity<String> deleteBookFromStudent(@PathVariable Long studentId, @PathVariable Long bookId) {
        String result = studentService.deleteBookFromStudent(studentId, bookId);
        return ResponseEntity.ok(result);
    }

}
