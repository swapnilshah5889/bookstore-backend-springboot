package com.swapnilshah5889.Bookstore.models.response;

public class DeleteAuthorResponse {

    int authorId;
    int bookCount;
    
    public DeleteAuthorResponse(int authorId, int bookCount) {
        this.authorId = authorId;
        this.bookCount = bookCount;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }    

}
