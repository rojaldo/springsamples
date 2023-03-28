package com.example.springSamples.response;

import java.util.List;

import com.example.springSamples.entities.CustomerEntity;
import com.fasterxml.jackson.annotation.JsonAlias;

// this class is a response to a request for a list of customers
public class CustomersResponse implements ICustomerResponse{
    List<CustomerEntity> customers;
    int count = 0;
    int status = 0;
    String message = "";

    public CustomersResponse(Iterable<CustomerEntity> customers) {
        this.customers = (List<CustomerEntity>) customers;
        this.count = this.customers.size();
        this.status = 200;
        this.message = "OK";
    }

    @Override
    public String toString() {
        return "CustomersResponse [customers=" + customers + ", count=" + count + ", status=" + status + ", message="
                + message + "]";
    }
    
}
