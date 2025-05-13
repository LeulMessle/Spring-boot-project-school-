package com.example.springproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.springproject.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
