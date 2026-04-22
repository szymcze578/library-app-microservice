package org.szymon.publication.Api.Models;

import org.szymon.publication.Domain.Enums.PublicationType;

/**
 * Lightweight DTO for search results. Contains just what the header dropdown needs:
 * enough to display the result and link through to the detail page.
 * Does NOT include description or other heavy fields.
 */
public record PublicationSearchResult(
        Long id,
        PublicationType type,
        String title,
        String author,
        String publisher,
        Integer publicationYear,
        String isbn,
        String issn,
        Integer availableCopies
) {}
