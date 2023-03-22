package com.example.springSamples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springSamples.entities.CustomerEntity;
import com.example.springSamples.repositories.CustomerRepository;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customers")
    public Iterable<CustomerEntity> getCustomers() {
        return repository.findAll();
    }

    @PostMapping("/customers")
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customer) {
        // check if customer exists
        CustomerEntity myCustomer = repository.findByEmail(customer.getEmail());
        if (myCustomer != null) {
            // return 409
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity already exists: " + customer.getEmail());
        }{
            // return 200
            return repository.save(customer);
        }
    }

    @PutMapping("/customers")
    public CustomerEntity updateCustomer(@RequestBody CustomerEntity customer) {
        // check if customer exists
        CustomerEntity myCustomer = repository.findById(customer.getId());
        if (myCustomer == null) {
            // return 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }else{
            return repository.save(customer);
        }
    }

    @DeleteMapping("/customers")
    public void deleteCustomer(@RequestParam(name = "id", required = true) long id) {
        // check if customer exists
        CustomerEntity customer = repository.findById(id);
        if (customer == null) {
            // return 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }else {
            repository.deleteById(id);

        }
    }
}
