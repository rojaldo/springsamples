package com.example.springSamples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springSamples.entities.CustomerEntity;
import com.example.springSamples.entities.SaleEntity;
import com.example.springSamples.repositories.CustomerRepository;
import com.example.springSamples.requests.SaleRequest;
import com.example.springSamples.services.SaleService;

@RestController
@RequestMapping("/api/v1/store")
public class SaleRestController {

    @Autowired
    private SaleService service;

    @GetMapping("/sales")
    public Iterable<SaleEntity> getSales() {
        return service.getSales();
    }

    @PostMapping("/sales")
    public SaleEntity createSale(@RequestBody SaleRequest sale) {
        // cheack that user exists
        return service.addNewSale(sale);
    }

    @PutMapping("/sales/{id}")
    public SaleEntity updateSale(@PathVariable long id, @RequestBody SaleRequest sale) {
        return service.updateSale(id, sale);
    }
    
    @GetMapping("/sales/{id}")
    public SaleEntity getSaleById(@PathVariable long id) {
        return service.getSaleById(id);
    }

    @DeleteMapping("/sales/{id}")
    public void deleteSale(@PathVariable long id) {
        service.deleteSale(id);
    }
}
