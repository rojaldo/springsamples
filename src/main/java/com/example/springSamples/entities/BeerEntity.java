package com.example.springSamples.entities;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */

@Entity
public class BeerEntity{
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long id;

    private String name;
    private String tagline;
    @JsonProperty("first_brewed")
    private String firstBrewed;
    private String description;
    @JsonProperty("image_url")
    private String imageUrl;
    private double abv;

    public BeerEntity() {
    }

    public BeerEntity(String name, String tagline, String firstBrewed, String description, String imageUrl,
            double abv) {
        this.name = name;
        this.tagline = tagline;
        this.firstBrewed = firstBrewed;
        this.description = description;
        this.imageUrl = imageUrl;
        this.abv = abv;
    }

    @Override
    public String toString() {
        return "BeerEntity [abv=" + abv + ", description=" + description + ", firstBrewed=" + firstBrewed + ", id=" + id
                + ", imageUrl=" + imageUrl + ", name=" + name + ", tagline=" + tagline + "]";
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getAbv() {
        return abv;
    }

    public String getDescription() {
        return description;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }



}


