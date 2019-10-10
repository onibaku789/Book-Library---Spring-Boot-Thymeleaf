package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Optional<Author> getAuthor(Integer authorId) {
        return authorRepository.findById(authorId);
    }

    @Override
    public void updateAuthor(Integer authorId, Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Integer authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public List<Author> getAllAuthors() {

        return new ArrayList<>(authorRepository.findAll());
    }
}
