package org.example.project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "magazines")
public class Magazine extends Publication{
    private String monthDay;
    private String language;
}
