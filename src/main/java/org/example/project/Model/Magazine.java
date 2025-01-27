package org.example.project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "magazines")
public class Magazine extends Publication{

    @NotNull(message = "Date (month and day) of magazine can not be empty")
    private String monthDay;

    @NotNull(message = "Language can not be empty")
    private String language;

    public Magazine() {}

    public Magazine(String title, String year, String publisher, String monthDay, String language) {
        super(title, year, publisher);
        this.monthDay = monthDay;
        this.language = language;
    }

}
