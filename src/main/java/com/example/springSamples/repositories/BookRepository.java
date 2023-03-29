package com.example.springSamples.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.springSamples.entities.BookEntity;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
    BookEntity findById(long id);
}
