package com.example.springproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("BOOKS")
public class Book {
    
    @Id
    private Long id;
    
    private String title;
    private String description;
    private Integer publicationYear;
    private String isbn;
    
    // Foreign key to Author
    private Long authorId;
}
