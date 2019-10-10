package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/rest")
public class AuthorRestController {

    public static final Logger logger = LoggerFactory.getLogger(AuthorRestController.class);


    private AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping(value = "/list_authors")
    public List<Author> getListAuthors() {


        return authorService.getAllAuthors();

    }

}
