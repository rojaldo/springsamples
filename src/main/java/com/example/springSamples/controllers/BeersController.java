package com.example.springSamples.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springSamples.entities.BeerEntity;
import com.example.springSamples.repositories.BeersRepository;
import com.example.springSamples.services.BeersService;

@RestController
@RequestMapping("/api/v1")
public class BeersController {

    @Autowired
    private BeersService beersService;


    @GetMapping("/beers")
    public Iterable<BeerEntity> getAllBeers() {
        return this.beersService.getAllBeers();
    }

    @GetMapping("/punkapi")
    public Iterable<BeerEntity> getBeersFromApi() {
        return this.beersService.getBeersFromApi();
    }
    
}
