package com.example.springproject.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.springproject.model.Author;
import com.example.springproject.model.Book;
import com.example.springproject.service.AuthorService;
import com.example.springproject.service.BookService;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AuthorService authorService;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @PostConstruct
    public void initDatabase() {
        // Check if tables exist, if not create them
        try {
            List<String> tables = jdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'",
                String.class
            );
            
            if (!tables.contains("AUTHORS")) {
                jdbcTemplate.execute(
                    "CREATE TABLE AUTHORS (" +
                    "ID BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "NAME VARCHAR(255) NOT NULL, " +
                    "BIOGRAPHY TEXT)"
                );
            }
            
            if (!tables.contains("BOOKS")) {
                jdbcTemplate.execute(
                    "CREATE TABLE BOOKS (" +
                    "ID BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "TITLE VARCHAR(255) NOT NULL, " +
                    "DESCRIPTION TEXT, " +
                    "PUBLICATION_YEAR INT, " +
                    "ISBN VARCHAR(20), " +
                    "AUTHOR_ID BIGINT NOT NULL, " +
                    "FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS(ID))"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Create some authors
        Author author1 = Author.builder()
                .name("George RR Martin")
                .biography("Infamous author, best known for the game of thrones series.")
                .build();
        
        Author author2 = Author.builder()
                .name("George Orwell")
                .biography("English novelist, essayist, and critic.")
                .build();
        
        Author author3 = Author.builder()
                .name("Masashi Kishimoto")
                .biography("Japanese manga artist known for works of shonen fiction.")
                .build();
        
        // Save authors
        author1 = authorService.save(author1);
        author2 = authorService.save(author2);
        author3 = authorService.save(author3);
        
        // Create some books
        Book book1 = Book.builder()
                .title("A Game of thrones")
                .description("The first book in the series.")
                .publicationYear(1996)
                .isbn("⭐⭐⭐⭐⭐")
                .authorId(author1.getId())
                .build();

        Book book2 = Book.builder()
                .title("A storm of swords")
                .description("The second book in the series.")
                .publicationYear(2000)
                .isbn("⭐⭐⭐⭐")
                .authorId(author1.getId())
                .build();

        Book book3 = Book.builder()
                .title("1984")
                .description("A dystopian novel set in a totalitarian regime.")
                .publicationYear(1949)
                .isbn("⭐⭐⭐")
                .authorId(author2.getId())
                .build();

        Book book4 = Book.builder()
                .title("Animal Farm")
                .description("An allegorical novella about the Russian Revolution.")
                .publicationYear(1945)
                .isbn("⭐⭐⭐⭐")
                .authorId(author2.getId())
                .build();

        Book book5 = Book.builder()
                .title("Naruto")
                .description("A action graphic novel.")
                .publicationYear(2000)
                .isbn("⭐⭐⭐⭐⭐")
                .authorId(author3.getId())
                .build();

        // Save books
        bookService.save(book1);
        bookService.save(book2);
        bookService.save(book3);
        bookService.save(book4);
        bookService.save(book5);
    }
}