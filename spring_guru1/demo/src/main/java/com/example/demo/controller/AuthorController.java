package com.example.demo.controller;

import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {

    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors")
    public String getBooks(Model model){
        //retrieve all authors and add to the model with key 'authors'
        model.addAttribute("authors", authorRepository.findAll());
        //"authors" is the view name in Thymeleaf
        return "authors";
    }

}
