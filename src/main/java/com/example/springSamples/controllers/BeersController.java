package com.example.springSamples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.springSamples.services.BeersService;


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
}
