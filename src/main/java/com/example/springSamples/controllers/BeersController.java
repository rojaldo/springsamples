package com.example.springSamples.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springSamples.entities.BeerEntity;
import com.example.springSamples.repositories.BeersRepository;
import com.example.springSamples.services.BeersService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
public class BeersController {

    @Autowired
    private BeersService beersService;


    @GetMapping("/beers")
    public Iterable<BeerEntity> getAllBeers(
            @RequestParam(name = "abv_gt", required = false, defaultValue = "0") String abvGt,
            @RequestParam(name = "abv_lt", required = false, defaultValue = "100") String abvLt) {
        try {
            double abvGtDouble = Double.parseDouble(abvGt);
            double abvLtDouble = Double.parseDouble(abvLt);
            if (abvGtDouble == 0 && abvLtDouble == 100) {
                return this.beersService.getAllBeers();
            } else {
                return this.beersService.getBeersByAbv(abvGtDouble, abvLtDouble);
            }
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid abv_gt or abv_lt value", e);
        }
    }


    @GetMapping("/beers/{id}")
    public BeerEntity getBeerById(@PathVariable(name = "id", required = true) long id, HttpServletResponse response) {
        BeerEntity beer = this.beersService.getBeerById(id);
        if (beer == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }else {
            return beer;
        }
    }

    @GetMapping("/punkapi")
    public Iterable<BeerEntity> getBeersFromApi() {
        return this.beersService.getBeersFromApi();
    }

    @PostMapping("/beers")
    public BeerEntity createBeer(@RequestBody BeerEntity beer, HttpServletResponse response) {
        BeerEntity beerEntity = this.beersService.getBeerByBeerName(beer.getName());
        if (beerEntity == null) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            return this.beersService.createBeer(beer);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return beer;
        }
    }

    @PutMapping("/beers/{id}")
    public BeerEntity updateBeer(@PathVariable(name = "id", required = true) long id, @RequestBody BeerEntity beer, HttpServletResponse response) {
        BeerEntity beerEntity = this.beersService.getBeerById(id);
        if (beerEntity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            return this.beersService.updateBeer(beer, id);
        }
    }

    @DeleteMapping("/beers/{id}")
    public void deleteBeer(@PathVariable(name = "id", required = true) long id, HttpServletResponse response) {
        BeerEntity beerEntity = this.beersService.getBeerById(id);
        if (beerEntity == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            this.beersService.deleteBeer(id);
        }
    }

    @DeleteMapping("/beers")
    public void deleteBeers(
        @RequestParam(name = "abv_gt", required = false, defaultValue = "-1") String abv_gt,
        @RequestParam(name = "abv_lt", required = false, defaultValue = "-1") String abv_lt,
        HttpServletResponse response) {
        double abvGtDouble = Double.parseDouble(abv_gt);
        double abvLtDouble = Double.parseDouble(abv_lt);
        if (abvGtDouble == -1 && abvLtDouble == -1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            abvGtDouble = abvGtDouble == -1 ? 0 : abvGtDouble;
            abvLtDouble = abvLtDouble == -1 ? 100 : abvLtDouble;
            this.beersService.deleteBeers(abvGtDouble, abvLtDouble);
        }
    }

}
