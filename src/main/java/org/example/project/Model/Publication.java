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
}
