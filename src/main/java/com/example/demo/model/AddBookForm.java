package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

public @Data
class AddBookForm {
    @NotNull
    private int authorId;
    @NotNull
    private int bookId;
    private Iterable<Book>  books;
    private Author author;

   public AddBookForm(Iterable<Book> books,Author author){
        this.author = author;
        this.books = books;
    }

}
