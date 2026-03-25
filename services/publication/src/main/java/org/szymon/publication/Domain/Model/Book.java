package org.szymon.publication.Domain.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Table(name = "book")
public class Book extends Publication {
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
