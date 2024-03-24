package com.swapnilshah5889.Bookstore.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnilshah5889.Bookstore.dao.BookDAO;
import com.swapnilshah5889.Bookstore.models.BookModel;

@Service
public class BookService {

    private BookDAO bookDAO;
    
    @Autowired
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    
    public List<BookModel> getAllBooks() {
        return this.bookDAO.findAllBooks();
    }

}
