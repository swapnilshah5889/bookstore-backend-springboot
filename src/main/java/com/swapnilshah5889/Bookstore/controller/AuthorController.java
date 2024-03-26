package com.swapnilshah5889.Bookstore.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.swapnilshah5889.Bookstore.models.response.ApiResponse;
import com.swapnilshah5889.Bookstore.services.AuthorService;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        ApiResponse authors = authorService.getAllAuthors();
        if(authors == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(!authors.isStatus()) {
            return new ResponseEntity(authors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(authors, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAllCategoryByID(@PathVariable("id") int id) {
        ApiResponse author = authorService.getAuthor(id);
        if(author == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(!author.isStatus()) {
            return new ResponseEntity(author, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(author, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestParam("name") String name) {
        ApiResponse author = authorService.insertAuthor(name);
        if(author == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(!author.isStatus()) {
            return new ResponseEntity(author, HttpStatus.INTERNAL_SERVER_ERROR);    
        }
        return new ResponseEntity(author, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateCategory(
        @RequestParam("id") int id,
        @RequestParam("name") String name
    ) {
        ApiResponse author = authorService.updateAuthor(id, name);
        if(author == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(!author.isStatus()) {
            return new ResponseEntity(author, HttpStatus.INTERNAL_SERVER_ERROR);    
        }
        return ResponseEntity.ok(author);
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteCategory(
        @RequestParam("id") int id
    ) {
        ApiResponse response = authorService.deleteAuthor(id);    
        if(response == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(!response.isStatus()) {
            return new ResponseEntity(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(
            response,
            HttpStatus.OK);
    }

}
