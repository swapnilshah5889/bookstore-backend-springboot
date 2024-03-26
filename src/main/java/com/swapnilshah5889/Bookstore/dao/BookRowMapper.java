package com.swapnilshah5889.Bookstore.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.swapnilshah5889.Bookstore.models.object.AuthorModel;
import com.swapnilshah5889.Bookstore.models.object.BookModel;
import com.swapnilshah5889.Bookstore.models.object.CategoryModel;

public class BookRowMapper implements RowMapper<BookModel>{

    @Override
    @Nullable
    public BookModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuthorModel author = new AuthorModel(
            rs.getInt("author_id"),
            rs.getString("author_name")
        );
        CategoryModel category = new CategoryModel(
            rs.getInt("category_id"),
            rs.getString("category_name")
        );
        BookModel bookModel = new BookModel(
            rs.getInt("book_id"),
            rs.getString("book_name"),
            category,
            rs.getInt("isbn"),
            author
        );
        return bookModel;
    }
}
