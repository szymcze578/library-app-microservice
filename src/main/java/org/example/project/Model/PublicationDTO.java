package org.example.project.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
