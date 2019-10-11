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


    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();




}


