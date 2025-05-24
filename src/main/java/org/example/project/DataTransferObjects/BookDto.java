package org.example.project.DataTransferObjects;

import jakarta.validation.constraints.NotNull;

public record BookDto(
        Long id,

        @NotNull(message = "Title is required.")
        String title,
        @NotNull(message = "Year is required.")
        String year,
        @NotNull(message = "Publisher is required.")
        String publisher,
        @NotNull(message = "Author is required.")
        String author,
        @NotNull(message = "Number of pages is required.")
        Integer pages,
        @NotNull(message = "Isbn number is required.")
        String isbn
) {
}
