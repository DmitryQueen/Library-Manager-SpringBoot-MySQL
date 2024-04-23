package com.pwr.restapi.config;


import com.pwr.restapi.entity.Book;
import com.pwr.restapi.entity.Student;
import com.pwr.restapi.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
//            List<Book> books = new ArrayList<>();
//            books.add(new Book("Harry Potter", "J.K Rowling"));
//            books.add(new Book("Goosebumps", "R.L Stine"));
//            Student alex = new Student( "Alex", "Jacked", 253003, "alex.jacked.04@gmail.com", books);
//            repository.save(alex);
//            books.clear();
//            books.add(new Book("Perry Mason", "Erle Stanley Gardner"));
//            books.add(new Book("Choose your own adventure", "Untitled"));
//            Student oscar = new Student("Oscar", "Hartmann", 275323, "oscar.hartmann.95@gmail.com", books);
//            repository.save(oscar);
        };
    }
}
