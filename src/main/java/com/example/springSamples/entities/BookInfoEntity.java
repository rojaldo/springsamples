package com.example.springSamples.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_info")
public class BookInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String author;
    private String title;
    private String genre;
    private String description;
    private String publisher;
    private String isbn;

    @OneToMany(mappedBy = "bookInfo")
    private List<BookEntity> book;

    public BookInfoEntity() {
    }

    public BookInfoEntity(String author, String title, String genre, String description, String publisher, String isbn) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "BookInfoEntity [author=" + author + ", description=" + description + ", genre=" + genre + ", isbn=" + isbn
                + ", publisher=" + publisher + ", title=" + title + "]";
    }
    
}
