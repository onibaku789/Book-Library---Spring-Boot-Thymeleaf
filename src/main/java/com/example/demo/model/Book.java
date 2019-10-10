package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public @Data
class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String book_name;
    private String ISBN;
    private String publish_year;
    private String publisher;

    @ManyToMany
    private Set<Author> authors = new HashSet<>();

    public void addAuthor(Author author) {
        authors.add(author);
    }


}


