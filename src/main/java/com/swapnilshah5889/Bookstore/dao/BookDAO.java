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

    public List<BookModel> findAllBooks() {
        String sql = "SELECT * FROM "+TABLE_NAME+";";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }
}
