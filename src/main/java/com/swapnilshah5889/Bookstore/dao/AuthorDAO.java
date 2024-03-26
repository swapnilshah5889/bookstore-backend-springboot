package com.swapnilshah5889.Bookstore.dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.swapnilshah5889.Bookstore.models.object.AuthorModel;
import com.swapnilshah5889.Bookstore.models.response.ApiResponse;
import com.swapnilshah5889.Bookstore.models.response.DeleteAuthorResponse;
import com.swapnilshah5889.Bookstore.services.BookService;

@Repository
public class AuthorDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BookService bookService;

    private final String TABLE_NAME = "authors";
    
    private final String SQL_GET_ALL_AUTHORS = "SELECT * FROM "+TABLE_NAME;
    private final String SQL_GET_AUTHOR = "SELECT * FROM "+TABLE_NAME+" WHERE author_id = ?";
    private final String SQL_INSERT_AUTHOR = "INSERT INTO "+TABLE_NAME+"(author_name) VALUES (?)";
    private final String SQL_UPDATE_AUTHOR = "UPDATE "+TABLE_NAME+" SET author_name = ? WHERE author_id = ?";
    private final String SQL_DELETE_AUTHOR = "DELETE FROM "+TABLE_NAME+" WHERE author_id = ?";

     // Get all authors
    public ApiResponse getAllAuthors() {
        try {
            List<AuthorModel> authors = jdbcTemplate.query(SQL_GET_ALL_AUTHORS, new AuthorRowMapper());
            return new ApiResponse()
                        .setSuccessResponse("Get all authors successful", authors);
        } catch (Exception e) {
            return new ApiResponse()
                    .setErrorResponse("Get all authors failed", e);
        }
    }

    // Get author by id
    public ApiResponse getAuthor(int id) {
        try {
        
            AuthorModel author = (AuthorModel) jdbcTemplate.queryForObject(SQL_GET_AUTHOR, new AuthorRowMapper(), id);
            return new ApiResponse()
                        .setSuccessResponse("Get author successful", author);
        } catch (Exception e) {
            return new ApiResponse()
                    .setErrorResponse("Get author failed", e);
        }
    }

    // Insert author
    public ApiResponse insertAuthor(String name) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(SQL_INSERT_AUTHOR, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, name);
                        return ps;
                    }      
                },
                keyHolder
            );
            return getAuthor(keyHolder.getKey().intValue())
                    .setMessage("Insert author successful");
            
        } catch (Exception e) {
            return new ApiResponse()
                    .setErrorResponse(
                        "Insert author failed", e);
        }
    }

    // Update Author
    public ApiResponse updateAuthor(int id, String name) {
        try {
            int updatedAuthors = jdbcTemplate.update(SQL_UPDATE_AUTHOR, name, id);
            if(updatedAuthors == 0) {
                return new ApiResponse()
                    .setErrorResponse("Author with author id "+id+" does not exist", null);
            }
            return new ApiResponse()
                    .setSuccessResponse(
                        "Update author successful", 
                        new AuthorModel(id, name)
                    );
        } catch (Exception e) {
            return new ApiResponse()
                    .setErrorResponse("Update author failed", e);
        }
    }

    // Delete Author
    public ApiResponse deleteAuthor(int authorId) {
        // Delete all books with this author
        int booksDeleted = bookService.deleteBooksByAuthor(authorId);
        int deleteAuthor = jdbcTemplate.update(SQL_DELETE_AUTHOR, authorId);
        ApiResponse response = new ApiResponse();
        if(deleteAuthor == 0) {
            response.setErrorResponse(
                "Author with id " + authorId + " does not exist", 
                null
            );
            return response;
        }
        response.setSuccessResponse(
            "Author with id " + authorId + " deleted successfully", 
            new DeleteAuthorResponse(authorId, booksDeleted)
        );
        return response;
    }

}
