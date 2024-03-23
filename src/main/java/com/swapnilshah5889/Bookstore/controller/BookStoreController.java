package com.swapnilshah5889.Bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookstore")
public class BookStoreController {

    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "UP";
    }
}
