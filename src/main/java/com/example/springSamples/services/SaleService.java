package com.example.springSamples.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springSamples.entities.BookEntity;
import com.example.springSamples.entities.CustomerEntity;
import com.example.springSamples.entities.SaleEntity;
import com.example.springSamples.repositories.SaleRepository;
import com.example.springSamples.requests.SaleRequest;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    public Iterable<SaleEntity> getSales() {
        return repository.findAll();
    }

    public SaleEntity getSaleById(long id) {
        return repository.findById(id);
    }

    public SaleEntity addNewSale(SaleRequest sale) {
        System.out.println(sale.toString());
        // check that user exists
        if (customerService.getCustomerById(sale.getCustomer()) == null) {
            throw new RuntimeException("Customer not found");
        }
        // check that book exists
        if (bookService.getBookById(sale.getBook()) == null) {
            throw new RuntimeException("Book not found");
        }
        return repository.save(new SaleEntity(sale.getDate(), sale.getPrice(), bookService.getBookById(sale.getBook()), customerService.getCustomerById(sale.getCustomer())));
    }

    public SaleEntity updateSale(long id, SaleRequest req) {
        // check that sale exists
        SaleEntity sale = getSaleById(id);
        if (sale == null) {
            throw new RuntimeException("Sale not found");
        }
        // check that user exists
        if (customerService.getCustomerById(req.getCustomer()) == null) {
            throw new RuntimeException("Customer not found");
        }
        // check that book exists
        if (bookService.getBookById(req.getBook()) == null) {
            throw new RuntimeException("Book not found");
        }
        sale.setDate(req.getDate());
        sale.setPrice(req.getPrice());
        sale.setCustomer(customerService.getCustomerById(req.getCustomer()));
        sale.setBook(bookService.getBookById(req.getBook()));

        return repository.save(sale);

    }

    public SaleEntity deleteSale(long id) {
        SaleEntity sale = getSaleById(id);
        if (sale == null) {
            throw new RuntimeException("Sale not found");
        }
        repository.delete(sale);
        return sale;
    }



}
