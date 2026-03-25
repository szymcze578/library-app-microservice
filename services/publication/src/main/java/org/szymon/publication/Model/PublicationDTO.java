package org.szymon.publication.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
