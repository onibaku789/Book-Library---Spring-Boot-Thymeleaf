package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBook(Integer bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public void updateBook(Integer BookId, Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);

    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }
}
