package com.example.springSamples.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springSamples.entities.CustomerEntity;
import com.example.springSamples.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Iterable<CustomerEntity> getCustomers() {
        return repository.findAll();
    }

    public CustomerEntity getCustomerById(long id) {
        return repository.findById(id);
    }

}