package com.swapnilshah5889.Bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.swapnilshah5889.Bookstore.models.object.CategoryModel;

public class CategoryRowMapper implements RowMapper{

    @Override
    @Nullable
    public CategoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CategoryModel(
            rs.getInt("category_id"),
            rs.getString("category_name")
        );
    }

}
