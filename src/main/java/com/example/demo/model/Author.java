package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public @Data
class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String name;
    private String dateOfBirth;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();


}
