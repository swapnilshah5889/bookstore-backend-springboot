package com.swapnilshah5889.Bookstore.services;
import com.swapnilshah5889.Bookstore.models.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.swapnilshah5889.Bookstore.dao.AuthorDAO;

@Service
public class AuthorService {

    @Autowired
    private AuthorDAO authorDAO;
    
    public ApiResponse getAllAuthors() {
        return this.authorDAO.getAllAuthors();
    }

    public ApiResponse getAuthor(int id) {
        return this.authorDAO.getAuthor(id);
    }

    public ApiResponse insertAuthor(String name) {
        return this.authorDAO.insertAuthor(name);
    }

    public ApiResponse updateAuthor(int id, String name) {
        return this.authorDAO.updateAuthor(id, name);
    }

    public ApiResponse deleteAuthor(int category_id) {
        return this.authorDAO.deleteAuthor(category_id);
    }

}
