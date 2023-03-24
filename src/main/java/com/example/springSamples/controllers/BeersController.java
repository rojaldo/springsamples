package com.example.springSamples.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Iterable<BeerEntity> getAllBeers(
            @RequestParam(name = "abv_gt", required = false, defaultValue = "0") String abv_gt,
            @RequestParam(name = "abv_lt", required = false, defaultValue = "100") String abv_lt) {
        double abvGtDouble = Double.parseDouble(abv_gt);
        double abvLtDouble = Double.parseDouble(abv_lt);
        if (abvGtDouble == 0 && abvLtDouble == 100) {
            return this.beersService.getAllBeers();
        } else {
            return this.beersService.getBeersByAbv(abvGtDouble, abvLtDouble);
        }
    }

    @GetMapping("/punkapi")
    public Iterable<BeerEntity> getBeersFromApi() {
        return this.beersService.getBeersFromApi();
    }

}
