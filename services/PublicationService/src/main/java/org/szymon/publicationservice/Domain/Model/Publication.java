package org.szymon.publicationservice.Domain.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Title can not be empty")
    private String title;

    @NotNull(message = "Year can not be empty")
    private String year;

    @NotNull(message = "Publisher can not be empty")
    private String publisher;

    public Publication() {}

    public Publication( String title, String year, String publisher) {
        this.title = title;
        this.year = year;
        this.publisher = publisher;
    }
}
