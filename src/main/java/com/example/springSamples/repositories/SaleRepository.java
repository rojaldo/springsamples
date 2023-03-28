package com.example.springSamples.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.springSamples.entities.SaleEntity;

public interface SaleRepository extends CrudRepository<SaleEntity, Long> {
    SaleEntity findById(long id);
    List<SaleEntity> findAll();
}
