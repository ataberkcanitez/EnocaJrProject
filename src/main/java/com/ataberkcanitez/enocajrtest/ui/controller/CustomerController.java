package com.ataberkcanitez.enocajrtest.ui.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @GetMapping
    public String getCustomer(){
        return "get customer was called.";
    }

    @PostMapping
    public String createCustomer(){
        return "create customer was called";
    }

    @PutMapping
    public String updateCustomer(){
        return "update customer was called";
    }



    @DeleteMapping
    public String deleteCustomer(){
        return "delete customer was called";
    }


}
