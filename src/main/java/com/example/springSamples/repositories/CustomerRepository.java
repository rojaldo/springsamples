package com.example.springSamples.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.springSamples.entities.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    CustomerEntity findById(long id);
    CustomerEntity findByEmail(String email);
    List<CustomerEntity> findByLastName(String lastName);
    List<CustomerEntity> findByFirstName(String firstName);
}
