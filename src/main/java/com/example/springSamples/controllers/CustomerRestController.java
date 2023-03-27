package com.example.springSamples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springSamples.entities.CustomerEntity;
import com.example.springSamples.repositories.CustomerRepository;
import com.example.springSamples.response.ErrorResponse;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/store")
public class CustomerRestController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customers")
    public Iterable<CustomerEntity> getCustomers() {
        return repository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable String id) {
        System.out.println("id: " + id);
        CustomerEntity customer = repository.findById(Long.parseLong(id));
        if (customer == null) {
            // return 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No existe el usuario con id: " + id, 404));
            
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerEntity customer, HttpServletResponse response) {
        // check if customer exists
        CustomerEntity myCustomer = repository.findByEmail(customer.getEmail());
        if (myCustomer != null) {
            // return 409
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Ya existe un usuario con el email: " + customer.getEmail(), 409));
        }
            // return 201
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(customer));
        
    }

    @PutMapping("/customers")
    public ResponseEntity<Object> updateCustomer(@RequestBody CustomerEntity customer) {
        // check if customer exists
        CustomerEntity myCustomer = repository.findById(customer.getId());
        if (myCustomer == null) {
            // return 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No existe el usuario con id: " + customer.getId(), 404));
        } else {
            // return 200
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(customer));
        }
    }

    @DeleteMapping("/customers")
    public ResponseEntity<Object> deleteCustomer(@RequestParam(name = "id", required = true) String id) {
        // check if customer exists
        System.out.println("id: " + id);
        CustomerEntity customer = repository.findById(Long.parseLong(id));
        System.out.println("customer: " + customer);
        if (customer == null) {
            // return 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No existe el usuario con id: " + id, 404));
        } else {
            // return 200
            repository.delete(customer);
            return ResponseEntity.status(HttpStatus.OK).body(customer);

        }
    }
}
