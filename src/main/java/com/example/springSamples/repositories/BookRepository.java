package com.example.springSamples.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.springSamples.entities.BookEntity;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
    BookEntity findById(long id);
    BookEntity findByIsbn(String isbn);
    List<BookEntity> findByTitle(String title);
    List<BookEntity> findByAuthor(String author);
}
