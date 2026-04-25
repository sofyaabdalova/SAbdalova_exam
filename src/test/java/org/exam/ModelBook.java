package org.exam;

public class ModelBook {
    public Integer id;
    public String isbn;
    public String title;
    public String author;
    public String genre;
    public Integer year;
    public Integer price;
    public Integer stock;
    public Integer pages;

    public ModelBook() {}
    public ModelBook(String isbn, String title, String author, String genre, Integer year, Integer price, Integer stock, Integer pages) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.price = price;
        this.stock = stock;
        this.pages = pages;
    }
}