package org.example.project.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

}
