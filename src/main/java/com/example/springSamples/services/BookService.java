package com.example.springSamples.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springSamples.entities.BookEntity;
import com.example.springSamples.repositories.BookInfoRepository;
import com.example.springSamples.repositories.BookRepository;
import com.example.springSamples.requests.BookRequest;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookInfoRepository infoRepository;

    public Iterable<BookEntity> getBooks() {
        return repository.findAll();
    }

    public BookEntity getBookById(long id) {
        return repository.findById(id);
    }

    public boolean bookInfoExists(BookRequest book) {
        return infoRepository.findById(book.bookInfoId) != null;
    }

    public BookEntity addNewBook(BookRequest book) {
        if (!bookInfoExists(book)) {
            throw new RuntimeException("Book info not found");
        }
        return repository.save(new BookEntity(book.price, book.storeCode, infoRepository.findById(book.bookInfoId)));
    }

    
}
