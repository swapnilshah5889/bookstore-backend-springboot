package com.swapnilshah5889.Bookstore.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swapnilshah5889.Bookstore.models.BookModel;

@Repository
public class BookDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private final String TABLE_NAME = "books";
    private final String SQL_GET_ALL_BOOKS = "SELECT * FROM "+TABLE_NAME+" AS b "+
                                            "JOIN categories AS c ON b.category_id = c.category_id "+
                                            "JOIN authors AS a on b.author_id = a.author_id";
    
    public List<BookModel> findAllBooks() {
        return jdbcTemplate.query(SQL_GET_ALL_BOOKS, new BookRowMapper());
    }
}
