package com.swapnilshah5889.Bookstore.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    private final String SQL_INSERT_BOOK = "INSERT INTO "+TABLE_NAME+" (book_name, author_id, category_id, isbn) "+
                                            "VALUES (?,?,?,?)";

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

    public ApiResponse findBooksByCategoryAndAuthor(Integer category_id, Integer author_id) {
        try {
            String query = SQL_GET_ALL_BOOKS + " WHERE ";
            if(category_id != null) {
                query += "c.category_id=" + category_id;
            }
            if(author_id != null) {
                if(category_id != null) 
                    query += " OR a.author_id=" + author_id;
                else 
                    query += " a.author_id=" + author_id;
            }

            List<BookModel> books = jdbcTemplate.query(query, new BookRowMapper());
            return new ApiResponse()
                    .setSuccessResponse("Find books successful", books);

        } catch (Exception e) {
            return new ApiResponse()
                    .setErrorResponse("Find books failed", e);
        }
    }
    
    public ApiResponse createBook(String bookName, String author_id, String category_id, int iSBN) {
        try {

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                new PreparedStatementCreator() {

                    @Override
                    public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(SQL_INSERT_BOOK, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, bookName);
                        ps.setString(2, author_id);
                        ps.setString(3, category_id);
                        ps.setInt(4, iSBN);
                        return ps;
                    }
                        
                },
                keyHolder
            );

            return findBookById(keyHolder.getKey().intValue());

        } catch (Exception e) {
            return new ApiResponse()
                .setErrorResponse("Create book failed", e);
        }
    }

    public ApiResponse updateBook(String book_id, String book_name, String category_id, String author_id, String iSBN) {
        try {

            String SQL_UPDATE = "UPDATE "+TABLE_NAME+" SET ";
            if(book_name != null) {
                SQL_UPDATE += " book_name = '"+ book_name + "' ";
            }
            if(category_id != null) {
                SQL_UPDATE += " category_id = " + category_id +" "; 
            }
            if(author_id != null) {
                SQL_UPDATE += " author_id = "+ author_id + " ";
            }
            if(iSBN != null) {
                SQL_UPDATE += " iSBN = " + iSBN + " ";
            }

            SQL_UPDATE += " WHERE book_id = "+book_id;
            final String SQL_UPDATE_QUERY = SQL_UPDATE;

            int updatedRows = jdbcTemplate.update(SQL_UPDATE_QUERY);

            if(updatedRows == 0) {
                return new ApiResponse()
                        .setSuccessResponse("Book update failed", null);
            }
            return findBookById(Integer.parseInt(book_id));
            
        } catch (Exception e) {
            return new ApiResponse()
                .setErrorResponse("Create book failed", e);
        }
    }

}
