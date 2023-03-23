package com.example.springSamples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springSamples.entities.CustomerEntity;
import com.example.springSamples.repositories.CustomerRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customers")
    public Iterable<CustomerEntity> getCustomers() {
        return repository.findAll();
    }

    @GetMapping("/customers/{id}")
    public CustomerEntity getCustomer(@PathVariable String id) {
        System.out.println("id: " + id);
        CustomerEntity customer = repository.findById(Long.parseLong(id));
        if (customer == null) {
            // return 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        } else {
            return customer;
        }
    }

    @PostMapping("/customers")
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customer, HttpServletResponse response) {
        // check if customer exists
        CustomerEntity myCustomer = repository.findByEmail(customer.getEmail());
        if (myCustomer != null) {
            // return 409
            // throw new ResponseStatusException(HttpStatus.CONFLICT, "entity already exists");
            // throw new BRException();
            GlobalControllerExceptionHandler handler = new GlobalControllerExceptionHandler();
            handler.handleConflictRepetedEmail();
        }
            // return 201
            response.setStatus(HttpServletResponse.SC_CREATED);
            return repository.save(customer);
        
    }

    @PutMapping("/customers")
    public CustomerEntity updateCustomer(@RequestBody CustomerEntity customer) {
        // check if customer exists
        CustomerEntity myCustomer = repository.findById(customer.getId());
        if (myCustomer == null) {
            // return 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        } else {
            return repository.save(customer);
        }
    }

    @DeleteMapping("/customers")
    public void deleteCustomer(@RequestParam(name = "id", required = true) long id) {
        // check if customer exists
        CustomerEntity customer = repository.findById(id);
        if (customer == null) {
            // return 404
            throw new BRException();
        } else {
            repository.deleteById(id);

        }
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    class BRException extends RuntimeException {
        public BRException() {
            super("Bad Request");
        }
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    class CRException extends RuntimeException {
        public CRException(String message) {
            super(message);
        }
    }

    @ControllerAdvice
    class GlobalControllerExceptionHandler {
        @ResponseStatus(HttpStatus.CONFLICT) // 409
        @ExceptionHandler(DataIntegrityViolationException.class)
        public void handleConflictRepetedEmail() {
            throw new CRException("Email already exists");

        }
    }
}
