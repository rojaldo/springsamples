package com.example.springSamples.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.springSamples.entities.BeerEntity;

public interface BeersRepository extends CrudRepository<BeerEntity, Long> {
    BeerEntity findById(long id);
    BeerEntity findByName(String beerName);
    //List<BeerEntity> findByAbvLessThan(double abv);
    //List<BeerEntity> findByAbvGreaterThanAndLessThan(double less, double greater);
}
