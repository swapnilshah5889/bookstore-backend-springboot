package com.swapnilshah5889.Bookstore.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.swapnilshah5889.Bookstore.models.object.CategoryModel;
import com.swapnilshah5889.Bookstore.models.response.DeleteCategoryResponse;
import com.swapnilshah5889.Bookstore.models.response.ApiResponse;
import com.swapnilshah5889.Bookstore.services.BookService;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class CategoryDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BookService bookService;

    private final String TABLE_NAME  = "categories";
    private final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM "+TABLE_NAME;
    private final String SQL_GET_CATEGORY = "SELECT * FROM "+TABLE_NAME+" WHERE category_id = ?";
    private final String SQL_INSERT_CATEGORY = "INSERT INTO "+TABLE_NAME+"(category_name) VALUES (?)";
    private final String SQL_UPDATE_CATEGORY = "UPDATE "+TABLE_NAME+" SET category_name = ? WHERE category_id = ?";
    private final String SQL_DELETE_CATEGORY = "DELETE FROM "+TABLE_NAME+" WHERE category_id = ?";
    
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

        return getCategory(keyHolder.getKey().intValue());
    }

    // Update category
    public CategoryModel updateCategory(int id, String name) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY, name, id);
        return new CategoryModel(id, name);
    }

    // Delete category
    public ApiResponse deleteCategory(int categoryId) {
        // Delete all books with this category
        int booksDeleted = bookService.deleteBooksByCategory(categoryId);
        int deletedCategory = jdbcTemplate.update(SQL_DELETE_CATEGORY, categoryId);
        ApiResponse response = new ApiResponse();
        if(deletedCategory == 0) {
            response.setErrorResponse(
                "Category with id " + categoryId + " does not exist", null);
            return response;
        }
        response.setSuccessResponse(
            "Category with id " + categoryId + " deleted successfully", 
            new DeleteCategoryResponse(categoryId, booksDeleted)
        );
        return response;
    }
}
