package com.example.springproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springproject.model.Author;
import com.example.springproject.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        authorRepository.findAll().forEach(authors::add);
        return authors;
    }
    
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
    
    public Author save(Author author) {
        return authorRepository.save(author);
    }
    
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
