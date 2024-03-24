package com.swapnilshah5889.Bookstore.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.swapnilshah5889.Bookstore.models.CategoryModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String TABLE_NAME  = "categories";
    private final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM "+TABLE_NAME;
    private final String SQL_GET_CATEGORY = "SELECT * FROM "+TABLE_NAME+" WHERE category_id = ?";
    private final String SQL_INSERT_CATEGORY = "INSERT INTO "+TABLE_NAME+"(category_name) VALUES (?)";
    
    // Get all categories
    public List<CategoryModel> getAllCategories() {
        return jdbcTemplate.query(SQL_GET_ALL_CATEGORIES, new CategoryRowMapper());
    }

    // Get category by id
    public CategoryModel getCategory(int id) {
        return (CategoryModel) jdbcTemplate.queryForObject(SQL_GET_CATEGORY, new CategoryRowMapper(), id);
    }

    // Insert category
    public CategoryModel insertCategory(String name) {
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
            new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(SQL_INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, name);
                    return ps;
                }
                    
            },
            keyHolder
        );

        return new CategoryModel(keyHolder.getKey().intValue(), name);
    }
}
