package com.example.demo.controller;


import com.example.demo.model.AddBookForm;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller

public class AuthorController {

    public static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private AuthorService authorService;
    private BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }


    @GetMapping("/list_authors")
    public ModelAndView ListAuthors(ModelAndView modelAndView) {
        List<Author> listAuthors = authorService.getAllAuthors();
        modelAndView.addObject("title", "Authors");
        modelAndView.addObject("listAuthors", listAuthors);
        modelAndView.setViewName("authors");

        return modelAndView;
    }

    @GetMapping("/add_author")
    public ModelAndView newAuthor(ModelAndView modelAndView) {
        modelAndView.addObject("title", "Save Authors");
        modelAndView.addObject("author", new Author());
        modelAndView.setViewName("addAuthor");

        return modelAndView;
    }

    @PostMapping("/save_author")
    public ModelAndView saveAuthor(ModelAndView modelAndView,
                                   @ModelAttribute @Valid Author author,
                                   Errors errors) {
        if (errors.hasErrors()) {
            modelAndView.addObject("title", "Save Authors");
            modelAndView.setViewName("addAuthor");
            return modelAndView;
        }

        if (author.getId() == 0) {
            authorService.addAuthor(author);
        } else {
            authorService.updateAuthor(author.getId(), author);
        }
        modelAndView.setViewName("redirect:/author/viewAuthor/" + author.getId());
        return modelAndView;
    }

    @GetMapping("/edit_author")
    public ModelAndView updateAuthor(ModelAndView modelAndView, HttpServletRequest request) {
        int authorId = Integer.parseInt(request.getParameter("id"));
        Optional<Author> author = authorService.getAuthor(authorId);
        modelAndView.setViewName("addAuthor");
        modelAndView.addObject("author", author);
        modelAndView.addObject("title", "Edit Author");

        return modelAndView;
    }

    @GetMapping("/delete_author")
    public ModelAndView deleteAuthor(ModelAndView modelAndView, HttpServletRequest request) {
        int authorId = Integer.parseInt(request.getParameter("id"));
        authorService.deleteAuthor(authorId);
        modelAndView.setViewName("redirect:/list_authors");

        return modelAndView;
    }

    @GetMapping("author/viewAuthor/{authorId}")
    public ModelAndView viewAuthor(ModelAndView modelAndView, @PathVariable int authorId) {

        logger.debug("GETTER VIEW AUTHOR id: " + authorId);
        if (authorService.getAuthor(authorId).isPresent()) {
            Author author = authorService.getAuthor(authorId).get();
            modelAndView.addObject("title", author.getName());
            modelAndView.addObject("books", author.getBooks());
            modelAndView.addObject("authorId", author.getId());
            modelAndView.setViewName("author/viewAuthor");
        } else {
            modelAndView.setViewName("redirect:/list_authors/");


        }

        return modelAndView;
    }

    @GetMapping("/addBook/{authorId}")
    public ModelAndView addBooks(ModelAndView modelAndView, @PathVariable Integer authorId) {
        logger.debug("GETTER ADD BOOK id: " + authorId);
        if (authorService.getAuthor(authorId).isPresent()) {
            Author author = authorService.getAuthor(authorId).get();
            AddBookForm form = new AddBookForm(bookService.getAllBooks(), author);
            modelAndView.addObject("title", "Add Books to " + author.getName());
            modelAndView.addObject("form", form);
            modelAndView.addObject("success", true);
            modelAndView.setViewName("addBook");
        } else {
            modelAndView.setViewName("redirect:/list_authors");
            modelAndView.addObject("success", false);
        }
        return modelAndView;
    }

    @PostMapping("/addBook")
    public ModelAndView addBook(ModelAndView modelAndView, @ModelAttribute @Valid AddBookForm addBookForm, Errors errors){
        logger.debug("POST ADD BOOK");
        if (errors.hasErrors()){
            modelAndView.addObject("form", addBookForm);
            modelAndView.setViewName("addBook");
            return modelAndView;
        }
        if(bookService.getBook(addBookForm.getBookId()).isPresent() && authorService.getAuthor(addBookForm.getAuthorId()).isPresent()) {
            Book book = bookService.getBook(addBookForm.getBookId()).get();
            Author author = authorService.getAuthor(addBookForm.getAuthorId()).get();
            authorService.addBook(author,book);
        }

        return modelAndView;
    }
}
