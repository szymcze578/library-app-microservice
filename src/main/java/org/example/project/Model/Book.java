package org.example.project.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name = "books")
public class Book extends Publication{
    private String author;
    private int pages;
    private String isbn;

    public Book( String title, String year, String publisher, String author, int pages, String isbn) {
        super( title, year, publisher);
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book() {}
}
