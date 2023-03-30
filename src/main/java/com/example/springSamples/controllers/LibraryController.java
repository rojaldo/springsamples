package com.example.springSamples.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springSamples.entities.CustomerEntity;
import com.example.springSamples.forms.CustomerForm;
import com.example.springSamples.services.LibraryService;

import jakarta.validation.Valid;
@Controller
public class LibraryController {

    @Autowired
    private LibraryService service;

    @GetMapping("/library")
    public String greeting(Model view) {
        view.addAttribute("books", service.getBookInfos());
        view.addAttribute("customers", service.getCustomers());
        return "library";
    }

    @GetMapping("/library/newcustomer")
    public String newCustomer(Model view) {
        view.addAttribute("customer", new CustomerEntity("uno","dos","tres",0));
        return "newcustomer";
    }

    @RequestMapping(value = "/library/newcustomer", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("customer") @Valid CustomerForm customer, BindingResult errors) {
        if (errors.hasErrors()) {
            return "newcustomer";
        }else {
            System.out.println("new customer: " + customer.toString());
            CustomerEntity myCustomer = new CustomerEntity(customer.getFirstName(), customer.getSecondName(), customer.getEmail(), customer.getAge());
            service.addNewCustomer(myCustomer);
        }
        return "redirect:/library";
    }



}
