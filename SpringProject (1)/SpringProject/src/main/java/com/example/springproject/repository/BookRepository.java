package com.example.springproject.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.springproject.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    
    @Query("SELECT * FROM BOOKS WHERE AUTHOR_ID = :authorId")
    List<Book> findByAuthorId(@Param("authorId") Long authorId);
}
