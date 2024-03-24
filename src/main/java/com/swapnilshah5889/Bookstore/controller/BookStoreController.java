package com.swapnilshah5889.Bookstore.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.swapnilshah5889.Bookstore.services.BookService;
import com.swapnilshah5889.Bookstore.models.BookModel;
@RestController
@RequestMapping("/api/v1/bookstore")
public class BookStoreController {

    private final BookService bookService;
    
    @Autowired
    public BookStoreController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "UP";
    }

    @GetMapping("/books") 
    public ResponseEntity<List<BookModel>> getAllBooks() {
        List<BookModel> books = bookService.getAllBooks();
        if(books == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
