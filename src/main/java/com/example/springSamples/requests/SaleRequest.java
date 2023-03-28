package com.example.springSamples.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.JsonAdapter;

public class SaleRequest {

    private String date;
    private long price;
    @JsonProperty("book_id")
    private long book;
    @JsonProperty("customer_id")
    private long customer;

    public SaleRequest() {
    }

    public SaleRequest(String date, long price, long book, long customer) {
        this.date = date;
        this.price = price;
        this.book = book;
        this.customer = customer;
    }


    public String getDate() {
        return date;
    }

    public long getPrice() {
        return price;
    }

    public long getBook() {
        return book;
    }

    public long getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "SaleRequest{" +
                "date='" + date + '\'' +
                ", price=" + price +
                ", book=" + book +
                ", customer=" + customer +
                '}';
    }    
}
