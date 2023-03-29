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
import com.example.springSamples.requests.BookRequest;
import com.example.springSamples.services.BookService;

@RestController
@RequestMapping("/api/v1/store")
public class BookRestController {

    @Autowired
    private BookService service;

    @GetMapping("/books")
    public Iterable<BookEntity> getBooks() {
        return service.getBooks();
    }

    @PostMapping("/books")
    public BookEntity createBook(@RequestBody BookRequest book) {
        // check if book exists
        if (service.bookInfoExists(book)) {
            // return 409
            return service.addNewBook(book);
        } else {
            // return 201
            
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity already exists");
        }
    }

}
