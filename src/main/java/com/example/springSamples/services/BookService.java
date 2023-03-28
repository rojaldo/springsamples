package com.example.springSamples.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springSamples.entities.BookEntity;
import com.example.springSamples.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Iterable<BookEntity> getBooks() {
        return repository.findAll();
    }

    public BookEntity getBookById(long id) {
        return repository.findById(id);
    }
    
}
