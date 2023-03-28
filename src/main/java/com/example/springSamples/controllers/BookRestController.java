package com.example.springSamples.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springSamples.entities.BookEntity;
import com.example.springSamples.repositories.BookRepository;

@RestController
@RequestMapping("/api/v1/store")
public class BookRestController {
    @Autowired
    private BookRepository repository;

    @GetMapping("/books")
    public Iterable<BookEntity> getBooks() {
        return repository.findAll();
    }

    @PostMapping("/books")
    public BookEntity createBook(@RequestBody BookEntity book) {
        // check if book exists
        BookEntity myBook = repository.findByIsbn(book.getIsbn());
        if (myBook != null) {
            // return 409
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity already exists");
        } else {
            // return 201
            return repository.save(book);
        }
    }

    @PutMapping("/books")
    public BookEntity updateBook(@RequestBody BookEntity book) {
        // check if book exists
        BookEntity myBook = repository.findById(book.getId());
        if (myBook == null) {
            // return 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        } else {
            return repository.save(book);
        }
    }

    @DeleteMapping("/books")
    public void deleteBook(@RequestParam(name = "id", required = true) long id) {
        // check if book exists
        BookEntity book = repository.findById(id);
        if (book == null) {
            // return 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        } else {
            repository.delete(book);
        }
    }
}
