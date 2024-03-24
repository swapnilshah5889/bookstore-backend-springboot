package com.swapnilshah5889.Bookstore.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.swapnilshah5889.Bookstore.models.CategoryModel;
import java.util.List;

@Repository
public class CategoryDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String TABLE_NAME  = "categories";
    private final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM "+TABLE_NAME;
    private final String SQL_GET_CATEGORY = "SELECT * FROM "+TABLE_NAME+" WHERE category_id = ?";
    
    // Get all categories
    public List<CategoryModel> getAllCategories() {
        return jdbcTemplate.query(SQL_GET_ALL_CATEGORIES, new CategoryRowMapper());
    }

    // Get category by id
    public CategoryModel getCategory(int id) {
        return (CategoryModel) jdbcTemplate.queryForObject(SQL_GET_CATEGORY, new CategoryRowMapper(), id);
    }

}
