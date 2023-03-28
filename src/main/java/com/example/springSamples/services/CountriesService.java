package com.example.springSamples.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springSamples.entities.CountryEntity;
import com.example.springSamples.repositories.CountriesRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class CountriesService {

    @Autowired
    private CountriesRepository countriesRepository;

    @Autowired
    private RestTemplate resTemplate;

    public Iterable<CountryEntity> getAllCountries() {
        return countriesRepository.findAll();
    }

    public CountryEntity getCountryById(long id) {
        return countriesRepository.findById(id);
    }

    public CountryEntity getCountryByName(String countryName) {
        return countriesRepository.findByName(countryName);
    }

    public Iterable<CountryEntity> getCountriesFromApi() {
        String name = "Spain";
        String capital = "Madrid";
        String region = "Europe";
        String subregion = "Southern Europe";
        int population = 46704314;
        float area = 505992.0f;
        String flag = "https://restcountries.com/data/esp.svg";
        String[] languages = { "Spanish" };
        String currency = "EUR";


        List<CountryEntity> result = new ArrayList<CountryEntity>();
        // create gson
         Gson gson = new Gson();
        // get json from api
         Object array = gson.fromJson(resTemplate.getForObject("https://restcountries.com/v3.1/all", String.class), Object.class);
         JsonArray jsonArray = gson.toJsonTree(array).getAsJsonArray();
         for (JsonElement jsonElement : jsonArray) {
             JsonObject jsonObject = jsonElement.getAsJsonObject();
             JsonObject nameObject = jsonObject.getAsJsonObject("name");
             JsonElement commonElement = nameObject.get("common");
 
             if (commonElement != null) {
                 String commonValue = commonElement.getAsString();
                 name = commonValue;
             } else {
             }

             JsonArray arrayObject = jsonObject.getAsJsonArray("capital");
                if (arrayObject != null) {
                    String capitalValue = arrayObject.get(0).getAsString();
                    capital = capitalValue;
                } else {
                }

                JsonElement regionObject = jsonObject.getAsJsonObject("region");
                if (regionObject != null) {
                    String regionValue = regionObject.getAsString();
                    region = regionValue;
                } else {
                }
                

         }

        
        return result;
    }
    
}
