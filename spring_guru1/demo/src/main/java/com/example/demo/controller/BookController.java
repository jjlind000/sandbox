package com.example.demo.controller;

import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){
        //retrieve all books and add to the model with key 'books'
        model.addAttribute("books", bookRepository.findAll());
        //"books" is the view name in Thymeleaf
        return "books";
    }

}
