package com.swapnilshah5889.Bookstore.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swapnilshah5889.Bookstore.models.object.BookModel;
import com.swapnilshah5889.Bookstore.services.BookService;
@RestController
@RequestMapping
public class IndexController {


    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "UP";
    }

}
