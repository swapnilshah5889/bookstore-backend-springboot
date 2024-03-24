package com.swapnilshah5889.Bookstore.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.swapnilshah5889.Bookstore.dao.CategoryDAO;
import com.swapnilshah5889.Bookstore.models.CategoryModel;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;
    
    public List<CategoryModel> getAllCategories() {
        return this.categoryDAO.getAllCategories();
    }

    public CategoryModel getCategory(int id) {
        return this.categoryDAO.getCategory(id);
    }

    public CategoryModel insertCategory(String name) {
        return this.categoryDAO.insertCategory(name);
    }

}
