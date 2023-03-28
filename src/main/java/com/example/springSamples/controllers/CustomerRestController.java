package com.example.springSamples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springSamples.entities.CustomerEntity;
import com.example.springSamples.repositories.CustomerRepository;
import com.example.springSamples.response.CustomersResponse;
import com.example.springSamples.response.CustomerErrorResponse;
import com.example.springSamples.response.ICustomerResponse;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/store")
public class CustomerRestController {
    
    @Autowired
    private CustomerRepository repository;
    
    @GetMapping("/customers")
    public ResponseEntity<Iterable<CustomerEntity>> getCustomers(
    @RequestParam(name = "name", required = false, defaultValue = "") String name,
    @RequestParam(name = "surname", required = false, defaultValue = "") String surname) {
        System.out.println("name: " + name);
        if (name.equals("") && surname.equals("")) {
            System.out.println("customers:" + new CustomersResponse(repository.findAll()));
            return ResponseEntity.status(HttpStatus.OK).body( repository.findAll());
        } else if (!name.equals("") && surname.equals("")) {
            return ResponseEntity.status(HttpStatus.OK).body( repository.findByFirstName(name));
        }else if (name.equals("") && !surname.equals("")) {
            return ResponseEntity.status(HttpStatus.OK).body( repository.findByLastName(surname));
            
        }else {
            return ResponseEntity.status(HttpStatus.OK).body( repository.findByFirstNameAndLastName(name, surname));
        }
    }
    
    
    @GetMapping("/customers/{id}")
    public ResponseEntity<ICustomerResponse> getCustomer(@PathVariable String id) {
        System.out.println("id: " + id);
        CustomerEntity customer = repository.findById(Long.parseLong(id));
        if (customer == null) {
            // return 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerErrorResponse("No existe el usuario con id: " + id, 404));
            
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        }
    }
    
    @PostMapping("/customers")
    public ResponseEntity<ICustomerResponse> createCustomer(@RequestBody CustomerEntity customer, HttpServletResponse response) {
        // check if customer exists
        CustomerEntity myCustomer = repository.findByEmail(customer.getEmail());
        if (myCustomer != null) {
            // return 409
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomerErrorResponse("Ya existe un usuario con el email: " + customer.getEmail(), 409));
        }
        // return 201
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(customer));
        
    }
    
    @PutMapping("/customers")
    public ResponseEntity<ICustomerResponse> updateCustomer(@RequestBody CustomerEntity customer) {
        // check if customer exists
        CustomerEntity myCustomer = repository.findById(customer.getId());
        if (myCustomer == null) {
            // return 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerErrorResponse("No existe el usuario con id: " + customer.getId(), 404));
        } else {
            // return 200
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(customer));
        }
    }
    
    @DeleteMapping("/customers")
    public ResponseEntity<ICustomerResponse> deleteCustomer(@RequestParam(name = "id", required = true) String id) {
        // check if customer exists
        System.out.println("id: " + id);
        CustomerEntity customer = repository.findById(Long.parseLong(id));
        System.out.println("customer: " + customer);
        if (customer == null) {
            // return 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerErrorResponse("No existe el usuario con id: " + id, 404));
        } else {
            // return 200
            repository.delete(customer);
            return ResponseEntity.status(HttpStatus.OK).body(customer);
            
        }
    }
}
