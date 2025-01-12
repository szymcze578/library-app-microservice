package org.example.project.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PublicationDTO {
    private long id;
    private String type;
    private String title;
    private String year;
    private String publisher;
    private String author;
    private int pages;
    private String isbn;
    private String monthDay;
    private String language;
}
