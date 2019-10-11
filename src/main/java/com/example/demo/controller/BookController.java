package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Controller("/book")
public class BookController {

    public static final Logger logger = LoggerFactory.getLogger(BookController.class);


    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/list_books")
    public ModelAndView ListBooks(ModelAndView modelAndView) {
        List<Book> listBooks = bookService.getAllBooks();
        modelAndView.addObject("listBooks", listBooks);
        modelAndView.setViewName("books");
        modelAndView.addObject("title", "Book lists");

        return modelAndView;
    }


    @GetMapping("/add_book")
    public ModelAndView newBook(ModelAndView modelAndView) {
        Book book = new Book();
        modelAndView.addObject("book", book);
        modelAndView.setViewName("book/addBook");
        modelAndView.addObject("title", "Add book");

        return modelAndView;
    }


    @PostMapping("/save_book")
    public ModelAndView saveBook(@ModelAttribute Book book) {
        if (book.getId() == 0) {
            bookService.addBook(book);
        } else {
            bookService.updateBook(book.getId(), book);
        }

        return new ModelAndView("redirect:/list_books");
    }


    @GetMapping("/edit_book")
    public ModelAndView updateBook(ModelAndView modelAndView, HttpServletRequest request) {
        int bookId = Integer.parseInt(request.getParameter("id"));
        Optional<Book> book = bookService.getBook(bookId);
        modelAndView.setViewName("book/addBook");
        modelAndView.addObject("book", book);
        modelAndView.addObject("title", "Edit book");

        return modelAndView;
    }


    @GetMapping("/delete_book")
    public ModelAndView deleteBook(HttpServletRequest request) {
        int bookId = Integer.parseInt(request.getParameter("id"));
        bookService.deleteBook(bookId);
        return new ModelAndView("redirect:/list_books");
    }
}
