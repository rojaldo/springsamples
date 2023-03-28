package com.example.springSamples.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.springSamples.entities.CountryEntity;

public interface CountriesRepository extends CrudRepository<CountryEntity, Long> {
    CountryEntity findById(long id);
    CountryEntity findByName(String beerName);
}
