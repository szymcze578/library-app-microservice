package org.szymon.publicationservice.Domain.Model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
public class Magazine extends Publication {

    private String monthDay;
    private String language;
    public Magazine() {}

    public Magazine(String title, String year, String publisher, String monthDay, String language) {
        super(title, year, publisher);
        this.monthDay = monthDay;
        this.language = language;
    }

}
