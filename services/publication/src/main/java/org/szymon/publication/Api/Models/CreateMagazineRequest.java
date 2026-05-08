package org.szymon.publication.Api.Models;

import jakarta.validation.constraints.*;
import org.szymon.publication.Domain.Enums.Periodicity;

import java.time.LocalDate;

public record CreateMagazineRequest(

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

        @NotNull(message = "Issue date is required")
        @PastOrPresent(message = "Issue date cannot be in the future")
        LocalDate issueDate,

        @NotBlank(message = "Issue number must not be blank")
        @Size(max = 50, message = "Issue number must be at most 50 characters")
        String issueNumber,

        @NotNull(message = "Periodicity is required")
        Periodicity periodicity,

        @Pattern(
                regexp = "^\\d{4}-\\d{3}[\\dX]$",
                message = "ISSN must match format NNNN-NNNX (e.g. 1234-5678)"
        )
        String issn

) {}
