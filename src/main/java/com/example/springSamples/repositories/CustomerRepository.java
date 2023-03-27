package com.example.springSamples.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springSamples.entities.CustomerEntity;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findById(long id);
    List<CustomerEntity> findByFirstName(String firstName);
    List<CustomerEntity> findByLastName(String lastName);
    CustomerEntity findByEmail(String email);
    List<CustomerEntity> findByFirstNameAndLastName(String firstName, String lastName);
    @Query("SELECT c FROM CustomerEntity c WHERE c.age > :age")
    List<CustomerEntity> searchOlderThan(@Param("age") int age);
}
