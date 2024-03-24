package com.swapnilshah5889.Bookstore.models;

import java.sql.ResultSet;
import java.util.List;

public class BookModel {
    int id;
    String bookName;
    int category_id;
    int ISBN;
    int author_id;

    public BookModel() {}

    public BookModel(int id, String bookName, int category_id, int iSBN, int author_id) {
        this.id = id;
        this.bookName = bookName;
        this.category_id = category_id;
        ISBN = iSBN;
        this.author_id = author_id;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public int getCategory_id() {
        return category_id;
    }
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    public int getISBN() {
        return ISBN;
    }
    public void setISBN(int iSBN) {
        ISBN = iSBN;
    }
    
}
