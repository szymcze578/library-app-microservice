package org.example.project.DataTransferObjects;

import jakarta.validation.constraints.NotNull;

public record MagazineViewModel(
        Long id,
        @NotNull(message = "Title is required.")
        String title,
        @NotNull(message = "Year is required.")
        String year,
        @NotNull(message = "Publisher is required.")
        String publisher,
        @NotNull(message = "Date (month and day) is required")
        String monthDay,
        @NotNull(message = "Language is required.")
        String language
) {
}
