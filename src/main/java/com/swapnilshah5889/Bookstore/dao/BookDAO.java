package com.swapnilshah5889.Bookstore.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swapnilshah5889.Bookstore.models.object.BookModel;

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
    
    // Find all books
    public List<BookModel> findAllBooks() {
        return jdbcTemplate.query(SQL_GET_ALL_BOOKS, new BookRowMapper());
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
    
}
