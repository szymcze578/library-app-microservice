package org.example.project.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name = "books")
public class Book extends Publication{
    private String author;
    private int pages;
    private String isbn;
}
