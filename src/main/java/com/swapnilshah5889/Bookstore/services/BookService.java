package com.swapnilshah5889.Bookstore.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnilshah5889.Bookstore.dao.BookDAO;
import com.swapnilshah5889.Bookstore.models.object.BookModel;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;
    
    // Get all books
    public List<BookModel> getAllBooks() {
        return this.bookDAO.findAllBooks();
    }

    // Delete books by category
    public int deleteBooksByCategory(int category_id) {
        return this.bookDAO.deleteByCategory(category_id);
    }

    // Delete books by category
    public int deleteBooksByAuthor(int author_id) {
        return this.bookDAO.deleteByAuthor(author_id);
    }

}
