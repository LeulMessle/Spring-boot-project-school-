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
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private AuthorService authorService;
    
    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book/list";
    }
    
    @GetMapping("/new")
    public String newBookForm(Model model) {
        List<Author> authors = authorService.findAll();
        
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authors);
        
        return "book/form";
    }
    
    @PostMapping
    public String saveBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }
    
    @GetMapping("/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/books";
        }
        
        Author author = authorService.findById(book.getAuthorId());
        
        model.addAttribute("book", book);
        model.addAttribute("author", author);
        
        return "book/view";
    }
    
    @GetMapping("/{id}/edit")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        List<Author> authors = authorService.findAll();
        
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        
        return "book/form";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
