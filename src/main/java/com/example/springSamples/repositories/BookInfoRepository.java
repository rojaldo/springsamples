package com.example.springSamples.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.springSamples.entities.BookInfoEntity;

public interface BookInfoRepository extends CrudRepository<BookInfoEntity, Long> {
    BookInfoEntity findById(long id);
    BookInfoEntity findByIsbn(String isbn);
    List<BookInfoEntity> findByTitle(String title);
}