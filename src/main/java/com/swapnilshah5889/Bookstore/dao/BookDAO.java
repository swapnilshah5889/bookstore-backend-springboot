package com.swapnilshah5889.Bookstore.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swapnilshah5889.Bookstore.models.object.BookModel;
import com.swapnilshah5889.Bookstore.models.response.ApiResponse;

@Repository
public class BookDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private final String TABLE_NAME = "books";
    private final String SQL_GET_ALL_BOOKS = "SELECT * FROM "+TABLE_NAME+" AS b "+
                                            "JOIN categories AS c ON b.category_id = c.category_id "+
                                            "JOIN authors AS a on b.author_id = a.author_id";
    private final String SQL_DELETE_BY_CATEGORY = "DELETE FROM "+TABLE_NAME+" WHERE category_id = ?";
    private final String SQL_DELETE_BY_AUTHOR = "DELETE FROM "+TABLE_NAME+" WHERE author_id = ?";
    private final String SQL_DELETE_BOOK = "DELETE FROM "+TABLE_NAME+" WHERE book_id = ?";
    private final String SQL_GET_BOOK_BY_ID = "SELECT * FROM "+TABLE_NAME+" AS b "+
                                            "JOIN categories AS c ON b.category_id = c.category_id "+
                                            "JOIN authors AS a on b.author_id = a.author_id " +
                                            "WHERE b.book_id = ?";
    
    // Find all books
    public List<BookModel> findAllBooks() {
        return jdbcTemplate.query(SQL_GET_ALL_BOOKS, new BookRowMapper());
    }

    // Find book by id
    public ApiResponse findBookById(int id) {
        try {
            
            BookModel book = null;
            try {
                book = jdbcTemplate.queryForObject(SQL_GET_BOOK_BY_ID, new BookRowMapper(), id);
            } catch (EmptyResultDataAccessException e) {
                return new ApiResponse()
                    .setErrorResponse("Book does not exist with book id: "+id, null);
            }
            if(book == null) {
                return new ApiResponse()
                    .setErrorResponse("Book does not exist with book id: "+id, null);
            }

            return new ApiResponse()
                .setSuccessResponse("Find book successfull", book);

        } catch (Exception e) {
            return new ApiResponse()
                    .setErrorResponse("Find book failed", e);
        }
    }

    // Delete books by category id
    public int deleteByCategory(int category_id) {
        int deletedRows = jdbcTemplate.update(SQL_DELETE_BY_CATEGORY, category_id);
        return deletedRows;
    }

    // Delete books by author id
    public int deleteByAuthor(int author_id) {
        int deletedRows = jdbcTemplate.update(SQL_DELETE_BY_AUTHOR, author_id);
        return deletedRows;
    }

    // Delete book by book id
    public ApiResponse deleteBook(int book_id) {
        try {
            int deletedBook = jdbcTemplate.update(SQL_DELETE_BOOK, book_id);
    
            if(deletedBook == 0) {
                return new ApiResponse()
                    .setErrorResponse("Delete book failed", null);
            }
    
            return new ApiResponse()
                    .setSuccessResponse("Delete book with book_id, "+book_id+" successful", null);            
        } catch (Exception e) {
            return new ApiResponse()
                    .setErrorResponse("Delete book failed", e);
        }

    }
    


}
