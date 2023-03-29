package com.example.springSamples.requests;

public class BookRequest {
    public double price;
    public String storeCode;
    public long bookInfoId;
    public long salesId;

    public BookRequest() {
    }

    public BookRequest(double price, String storeCode, long bookInfoId, long salesId) {
        this.price = price;
        this.storeCode = storeCode;
        this.bookInfoId = bookInfoId;
        this.salesId = salesId;
    }

    @Override
    public String toString() {
        return "BookRequest{" +
                "price=" + price +
                ", storeCode='" + storeCode + '\'' +
                ", bookInfoId=" + bookInfoId +
                ", salesId=" + salesId +
                '}';
    }
}
