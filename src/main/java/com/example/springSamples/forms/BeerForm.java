package com.example.springSamples.forms;

import jakarta.validation.constraints.PositiveOrZero;

public class BeerForm {
    // @Size(min = 2, max = 50)
    private String name;
    private String tagline;
    private String description;
    private String imageUrl;
    private String firstBrewed;
    @PositiveOrZero
    private double abv;
    @PositiveOrZero
    private double ibu;
    @PositiveOrZero
    private double ebc;

    public BeerForm() {
    }

    public BeerForm(String name, String tagline, String description, String imageUrl, String firstBrewed, double abv, double ibu, double ebc) {
        this.name = name;
        this.tagline = tagline;
        this.description = description;
        this.imageUrl = imageUrl;
        this.firstBrewed = firstBrewed;
        this.abv = abv;
        this.ibu = ibu;
        this.ebc = ebc;
    }
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public double getIbu() {
        return ibu;
    }

    public void setIbu(double ibu) {
        this.ibu = ibu;
    }

    public double getEbc() {
        return ebc;
    }

    public void setEbc(double ebc) {
        this.ebc = ebc;
    }

    @Override
    public String toString() {
        return "BeerForm [abv=" + abv + ", description=" + description + ", ebc=" + ebc + ", firstBrewed=" + firstBrewed
                + ", ibu=" + ibu + ", imageUrl=" + imageUrl + ", name=" + name + ", tagline=" + tagline + "]";
    }
}
