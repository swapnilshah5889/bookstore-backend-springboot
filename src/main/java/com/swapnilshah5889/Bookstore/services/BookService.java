package com.swapnilshah5889.Bookstore.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnilshah5889.Bookstore.dao.BookDAO;
import com.swapnilshah5889.Bookstore.models.object.BookModel;
import com.swapnilshah5889.Bookstore.models.response.ApiResponse;

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

    // Find book by id
    public ApiResponse findBookById(int id) {
        return this.bookDAO.findBookById(id);
    }

    // Find books by category id and author id
    public ApiResponse findBooksByCategoryAndAuthor(Integer category_id, Integer author_id) {
        return this.bookDAO.findBooksByCategoryAndAuthor(category_id, author_id);
    }

    // Insert book
    public ApiResponse insertBook(String book_name, String author_id, String category_id, int iSBN) {
        return this.bookDAO.createBook(book_name, author_id, category_id, iSBN);
    }
}
