package org.szymon.publication.Api.Models;

import org.szymon.publication.Domain.Enums.Periodicity;
import org.szymon.publication.Domain.Model.Magazine;

import java.time.LocalDate;

public record MagazineResponse(
        Long id,
        String title,
        Integer publicationYear,
        String publisher,
        String language,
        String category,
        String description,
        String issn,
        String coverImageUrl,
        String issueNumber,
        LocalDate issueDate,
        Periodicity periodicity,
        Integer availableCopies
) {

    public static MagazineResponse from(Magazine magazine, int availableCopies) {
        return new MagazineResponse(
                magazine.getId(),
                magazine.getTitle(),
                magazine.getPublicationYear(),
                magazine.getPublisher(),
                magazine.getLanguage(),
                magazine.getCategory(),
                magazine.getDescription(),
                magazine.getIssn(),
                magazine.getCoverImageKey() == null
                        ? null
                        : "/api/publications/" + magazine.getId() + "/cover",
                magazine.getIssueNumber(),
                magazine.getIssueDate(),
                magazine.getPeriodicity(),
                availableCopies
        );
    }
}
