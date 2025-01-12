package org.example.project.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "publications")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String year;
    private String publisher;

    public Publication() {}

    public Publication( String title, String year, String publisher) {
        this.title = title;
        this.year = year;
        this.publisher = publisher;
    }
}
