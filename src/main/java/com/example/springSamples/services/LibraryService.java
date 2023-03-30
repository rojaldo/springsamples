package com.example.springSamples.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springSamples.entities.BookEntity;
import com.example.springSamples.entities.BookInfoEntity;
import com.example.springSamples.entities.CustomerEntity;
import com.example.springSamples.repositories.BookInfoRepository;
import com.example.springSamples.repositories.CustomerRepository;

@Service
public class LibraryService {

    @Autowired
    private BookInfoRepository infoRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<BookInfoEntity> getBookInfos() {
        return infoRepository.findAll();
    }

    public Iterable<CustomerEntity> getCustomers() {
        return customerRepository.findAll();
    }

    public void addNewCustomer(CustomerEntity customer) {
        customerRepository.save(customer);
    }
    
}
