package com.swapnilshah5889.Bookstore.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping
public class IndexController {


    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "UP";
    }

}
