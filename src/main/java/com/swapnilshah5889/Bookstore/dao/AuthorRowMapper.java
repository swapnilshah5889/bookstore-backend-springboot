package com.swapnilshah5889.Bookstore.dao;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.swapnilshah5889.Bookstore.models.object.AuthorModel;

public class AuthorRowMapper implements RowMapper<AuthorModel>{

    @Override
    @Nullable
    public AuthorModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AuthorModel(
            rs.getInt("author_id"),
            rs.getString("author_name")
        );
    }
    
}
