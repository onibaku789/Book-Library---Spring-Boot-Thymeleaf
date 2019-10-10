package com.example.demo.controller;


import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Controller
public class AuthorController {

    public static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @RequestMapping(value = "/list_authors", method = RequestMethod.GET)
    public ModelAndView ListAuthors(ModelAndView modelAndView) {

        List<Author> listAuthors = authorService.getAllAuthors();
        modelAndView.addObject("title", "Authors");
        modelAndView.addObject("listAuthors", listAuthors);
        modelAndView.setViewName("authors");

        return modelAndView;
    }

    @RequestMapping(value = "/add_author", method = RequestMethod.GET)
    public ModelAndView newAuthor(ModelAndView modelAndView) {
        Author author = new Author();
        modelAndView.addObject("title", "Save Authors");
        modelAndView.addObject("author", author);
        modelAndView.setViewName("addAuthor");

        return modelAndView;
    }

    @RequestMapping(value = "/save_author", method = RequestMethod.POST)
    public ModelAndView saveAuthor(ModelAndView modelAndView, @ModelAttribute Author author) {
        if (author.getId() == 0) {
            authorService.addAuthor(author);
        } else {
            authorService.updateAuthor(author.getId(), author);
        }
        modelAndView.setViewName("redirect:/list_authors");
        return modelAndView;
    }

    @RequestMapping(value = "/edit_author", method = RequestMethod.GET)
    public ModelAndView updateAuthor(ModelAndView modelAndView, HttpServletRequest request) {
        int authorId = Integer.parseInt(request.getParameter("id"));
        Optional<Author> author = authorService.getAuthor(authorId);
        modelAndView.setViewName("addAuthor");
        modelAndView.addObject("author", author);
        modelAndView.addObject("title", "Edit Author");

        return modelAndView;
    }

    @RequestMapping(value = "/delete_author", method = RequestMethod.GET)
    public ModelAndView deleteAuthor(ModelAndView modelAndView, HttpServletRequest request) {
        int authorId = Integer.parseInt(request.getParameter("id"));
        authorService.deleteAuthor(authorId);
        modelAndView.setViewName("redirect:/list_authors");
        return modelAndView;
    }
}
