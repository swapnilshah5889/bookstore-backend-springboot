package com.swapnilshah5889.Bookstore.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.swapnilshah5889.Bookstore.models.object.CategoryModel;
import com.swapnilshah5889.Bookstore.models.response.ApiResponse;
import com.swapnilshah5889.Bookstore.services.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategories() {
        List<CategoryModel> categories = categoryService.getAllCategories();
        if(categories == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAllCategoryByID(@PathVariable("id") int id) {
        ApiResponse category = categoryService.getCategory(id);
        if(!category.isStatus()) {
            return new ResponseEntity<>( category, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestParam("name") String name) {
        ApiResponse category = categoryService.insertCategory(name);
        if(category == null || !category.isStatus()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<CategoryModel> updateCategory(
        @RequestParam("id") int id,
        @RequestParam("name") String name
        ) {
        CategoryModel category = categoryService.updateCategory(id, name);
        if(category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteCategory(
        @RequestParam("id") int id
    ) {
        ApiResponse response = categoryService.deleteCategory(id);    
        
        if(!response.isStatus()) {
            return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(
            response,
            HttpStatus.OK);
    }

}
