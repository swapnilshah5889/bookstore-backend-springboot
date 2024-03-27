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
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.swapnilshah5889.Bookstore.models.object.BookModel;
import com.swapnilshah5889.Bookstore.models.response.ApiResponse;
import com.swapnilshah5889.Bookstore.services.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() {
        List<BookModel> books = bookService.getAllBooks();
        if(books == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBookById(
        @PathVariable("id") int id
    ) {
        ApiResponse response = bookService.findBookById(id);
        if(response == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(!response.isStatus()) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);    
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse> getBooksByCategoryAndAuthor(
        @RequestParam(value="category", required = false) Integer category_id,
        @RequestParam(value = "author", required = false) Integer author_id
    ) {
        if(category_id == null && author_id == null) {
            return new ResponseEntity<>(
                new ApiResponse().setErrorResponse("category or author required", null),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        ApiResponse response = bookService.findBooksByCategoryAndAuthor(category_id, author_id);
        if(response == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(!response.isStatus()) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);    
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add") 
    public ResponseEntity<ApiResponse> addBook(
        @RequestParam("bookName") String bookName, 
        @RequestParam("authorId") String authorId, 
        @RequestParam("categoryId") String categoryId,
        @RequestParam("iSBN") int isbn
    ) {
        ApiResponse response = bookService.insertBook(bookName, authorId, categoryId, isbn);
        if(response == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else if(!response.isStatus()) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);    
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateBook (
        @RequestParam(value = "bookId", required = true) String book_id,
        @RequestParam(value = "bookName", required = false) String bookName, 
        @RequestParam(value = "authorId", required = false) String authorId, 
        @RequestParam(value = "categoryId", required = false) String categoryId,
        @RequestParam(value = "iSBN", required = false) String isbn
    ) {
        ApiResponse response = bookService.updateBook(book_id, bookName, authorId, categoryId, isbn);
        if(response == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else if(!response.isStatus()) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);    
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteBookById(
        @RequestParam("bookId") int bookId
    ) {
        ApiResponse response = bookService.deleteBookById(bookId);
        if(response == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else if (!response.isStatus()) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);    
        }
        return ResponseEntity.ok(response);
    }

}
