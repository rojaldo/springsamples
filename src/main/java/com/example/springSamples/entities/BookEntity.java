package com.example.springSamples.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="BOOK")
public class BookEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double price;
    private String storeCode;

    @OneToMany(mappedBy = "book")
    private List<SaleEntity> sales;

    @ManyToOne
    @JoinColumn(name = "bookInfo_id")
    private BookInfoEntity bookInfo;

    public BookEntity() {
    }

    public BookEntity(double price, String storeCode, BookInfoEntity bookInfo) {
        this.price = price;
        this.storeCode = storeCode;
        this.bookInfo = bookInfo;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getStoreCode() {
        return storeCode;
    }

    @Override
    public String toString() {
        return "BookEntity [bookInfo=" + bookInfo + ", id=" + id + ", price=" + price + ", sales=" + sales + ", storeCode="
                + storeCode + "]";
    }

}
