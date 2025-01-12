package org.example.project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "magazines")
public class Magazine extends Publication{
    private String monthDay;
    private String language;

    public Magazine() {}

    public Magazine(String title, String year, String publisher, String monthDay, String language) {
        super(title, year, publisher);
        this.monthDay = monthDay;
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(String monthDay) {
        this.monthDay = monthDay;
    }
}
