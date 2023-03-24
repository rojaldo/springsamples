package com.example.springSamples.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springSamples.entities.BeerEntity;
import com.example.springSamples.repositories.BeersRepository;
import com.example.springSamples.response.BeerResponse;
import org.springframework.web.client.RestTemplate;

@Service
public class BeersService {

    @Autowired
    private BeersRepository beersRepository;

    @Autowired
    private RestTemplate resTemplate;

    public Iterable<BeerEntity> getAllBeers() {
        return beersRepository.findAll();
    }


    public BeerEntity getBeerById(long id) {
        return beersRepository.findById(id);
    }

    public BeerEntity getBeerByBeerName(String beerName) {
        return beersRepository.findByName(beerName);
    }   

    public Iterable<BeerEntity> getBeersFromApi() {
        List<BeerEntity> result = new ArrayList<BeerEntity>();
        BeerResponse[] beers = resTemplate.getForObject("https://api.punkapi.com/v2/beers", BeerResponse[].class);
        System.out.println(beers);
        for(BeerResponse beer : beers) {
            BeerEntity beerEntity = new BeerEntity(beer.getName(), beer.getTagline(), beer.getFirstBrewed(), beer.getDescription(), beer.getImageUrl(), beer.getAbv());
            result.add(beerEntity);
        }
        
        return result;
    }


    public Iterable<BeerEntity> getBeersByAbv(double abvGtDouble, double abvLtDouble) {
        List<BeerEntity> result = new ArrayList<BeerEntity>();
        Iterable<BeerEntity> beers = beersRepository.findAll();
        for(BeerEntity beer : beers) {
            if (beer.getAbv() > abvGtDouble && beer.getAbv() < abvLtDouble) {
                result.add(beer);
            }
        }
        return result;
    }


    
}
