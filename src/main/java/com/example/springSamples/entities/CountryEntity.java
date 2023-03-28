package com.example.springSamples.entities;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long id;

    private String name;
    private String capital;
    private String currency;
    private String region;
    @PositiveOrZero
    private int population;
    @PositiveOrZero
    private float area;
    private String[] languages;
    @URL
    private String flag;

    public CountryEntity() {
    }

    public CountryEntity(String name, String capital, String currency, String region, int population, float area,
            String[] languages, String flag) {
        this.name = name;
        this.capital = capital;
        this.currency = currency;
        this.region = region;
        this.population = population;
        this.area = area;
        this.languages = languages;
        this.flag = flag;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getCurrency() {
        return currency;
    }

    public String getRegion() {
        return region;
    }

    public int getPopulation() {
        return population;
    }

    public float getArea() {
        return area;
    }

    public String[] getLanguages() {
        return languages;
    }

    public String getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return "CountryEntity [area=" + area + ", capital=" + capital + ", currency=" + currency + ", flag=" + flag
                + ", id=" + id + ", languages=" + languages + ", name=" + name + ", population=" + population
                + ", region=" + region + "]";
    }
    
    
}
