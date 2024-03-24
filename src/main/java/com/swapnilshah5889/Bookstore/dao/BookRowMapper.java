package com.swapnilshah5889.Bookstore.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import com.swapnilshah5889.Bookstore.models.BookModel;

public class BookRowMapper implements RowMapper<BookModel>{

    @Override
    @Nullable
    public BookModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookModel bookModel = new BookModel(
            rs.getInt("book_id"),
            rs.getString("book_name"),
            rs.getInt("category_id"),
            rs.getInt("isbn"),
            rs.getInt("author_id")
        );
        return bookModel;
    }
}
