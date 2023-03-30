package com.example.springSamples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springSamples.entities.BeerEntity;
import com.example.springSamples.forms.BeerForm;
import com.example.springSamples.forms.CustomerForm;
import com.example.springSamples.services.BeersService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;


@Controller
public class BeersController {
    
    @Autowired
    private BeersService service;

    @GetMapping("/beers")
    public String getBeers(Model view) {
        view.addAttribute("beers", service.getAllBeers());
        return "beers";
    }

    @GetMapping("/beers/{id}")
    public String getBeerById(@PathVariable("id") long id, Model view) {
        view.addAttribute("beer", service.getBeerById(id));
        return "beer";
    }

    @GetMapping("/beers/delete/{id}")
    public String deleteBeerById(@PathVariable("id") long id, Model view) {
        service.deleteBeerById(id);
        return "redirect:/beers";
    }

    @GetMapping("/beers/{id}/edit")
    public String editBeerById(@PathVariable("id") long id, Model view) {
        view.addAttribute("beer", service.getBeerById(id));
        return "editbeer";
    }

    @GetMapping("/beers/addbeer")
    public String addBeer(Model view) {
        view.addAttribute("beer", new BeerForm());
        return "addbeer";
    }

    @PostMapping("/beers/addbeer")
    public String addBeer(@ModelAttribute("beer") @Valid BeerForm beerForm, BindingResult errors) {
        if (errors.hasErrors()) {
            return "/beers";
        }else {BeerEntity beer = new BeerEntity(
            beerForm.getName(), beerForm.getTagline(), beerForm.getFirstBrewed(), 
            beerForm.getDescription(), beerForm.getImageUrl(), beerForm.getAbv(), 
            beerForm.getIbu(), beerForm.getEbc());
        service.createBeer(beer);
        }
        return "redirect:/beers";
    }
}
