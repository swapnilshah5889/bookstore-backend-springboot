package com.swapnilshah5889.Bookstore.models.response;
import jakarta.annotation.Nullable;

public class DeleteCategoryResponse {

    int categoryId;
    int bookCount;    

    public DeleteCategoryResponse(int categoryId, int bookCount) {
        this.categoryId = categoryId;
        this.bookCount = bookCount;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public int getBookCount() {
        return bookCount;
    }
    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

}
