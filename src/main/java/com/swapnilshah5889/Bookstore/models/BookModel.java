package com.swapnilshah5889.Bookstore.models;
public class BookModel {
    int id;
    String bookName;
    CategoryModel category;
    int ISBN;
    AuthorModel author;

    public BookModel() {}

    public BookModel(int id, String bookName, CategoryModel categoryModel, int iSBN, AuthorModel authorModel) {
        this.id = id;
        this.bookName = bookName;
        this.category = categoryModel;
        ISBN = iSBN;
        this.author = authorModel;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int iSBN) {
        ISBN = iSBN;
    }

    public int getId() {
        return id;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    
    
}
