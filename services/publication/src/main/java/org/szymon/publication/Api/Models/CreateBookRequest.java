package org.szymon.publication.Api.Models;

import jakarta.validation.constraints.*;

import java.time.Year;

public record CreateBookRequest(

        @NotBlank(message = "Title must not be blank")
        @Size(max = 255, message = "Title must be at most 255 characters")
        String title,

        @NotNull(message = "Publication year is required")
        @Min(value = 1000, message = "Publication year must be after 1000")
        @Max(value = 9999, message = "Publication year must be a 4-digit year")
        Integer publicationYear,

        @NotBlank(message = "Publisher must not be blank")
        @Size(max = 150, message = "Publisher must be at most 150 characters")
        String publisher,

        @NotBlank(message = "Language must not be blank")
        @Size(min = 2, max = 10, message = "Language must be an ISO code")
        String language,

        @Size(max = 100, message = "Category must be at most 100 characters")
        String category,

        String description,

        @NotBlank(message = "Author must not be blank")
        @Size(max = 200, message = "Author must be at most 200 characters")
        String author,

        @NotNull(message = "Number of pages is required")
        @Positive(message = "Pages must be a positive number")
        Integer pages,

        @Pattern(
                regexp = "^(?:\\d{9}[\\dX]|\\d{13})$",
                message = "ISBN must be valid ISBN-10 (10 chars, last may be X) or ISBN-13 (13 digits)"
        )
        String isbn

) {
    public void validate() {
        if (publicationYear != null && publicationYear > Year.now().getValue()) {
            throw new IllegalArgumentException("Publication year cannot be in the future");
        }
    }
}
