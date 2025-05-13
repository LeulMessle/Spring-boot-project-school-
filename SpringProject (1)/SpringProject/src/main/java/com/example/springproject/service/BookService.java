package com.example.springproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springproject.model.Book;
import com.example.springproject.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }
    
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    
    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
    
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
