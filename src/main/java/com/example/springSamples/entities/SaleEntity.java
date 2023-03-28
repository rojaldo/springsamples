package com.example.springSamples.entities;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String date;
    private long price;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    public SaleEntity() {
    }

    public SaleEntity(String date, long price, BookEntity book, CustomerEntity customer) {
        this.date = date;
        this.price = price;
        this.book = book;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public long getPrice() {
        return price;
    }

    public BookEntity getBook() {
        return book;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "SaleEntity [book=" + book + ", customer=" + customer + ", date=" + date + ", id=" + id + ", price=" + price
                + "]";
    }    
}
