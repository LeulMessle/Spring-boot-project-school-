package com.example.springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springproject.model.Author;
import com.example.springproject.model.Book;
import com.example.springproject.service.AuthorService;
import com.example.springproject.service.BookService;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public String listAuthors(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "author/list";
    }
    
    @GetMapping("/new")
    public String newAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/form";
    }
    
    @PostMapping
    public String saveAuthor(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }
    
    @GetMapping("/{id}")
    public String viewAuthor(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id);
        if (author == null) {
            return "redirect:/authors";
        }
        
        List<Book> books = bookService.findByAuthorId(id);
        
        model.addAttribute("author", author);
        model.addAttribute("books", books);
        
        return "author/view";
    }
    
    @GetMapping("/{id}/edit")
    public String editAuthorForm(@PathVariable Long id, Model model) {
        Author author = authorService.findById(id);
        model.addAttribute("author", author);
        return "author/form";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}
