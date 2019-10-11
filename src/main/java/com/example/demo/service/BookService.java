package com.example.demo.service;

import com.example.demo.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void addBook(Book book);

    Optional<Book> getBook(Integer bookId);

    void updateBook(Integer BookId, Book book);

    void deleteBook(Integer bookId);

    List<Book> getAllBooks();


}
