package org.szymon.publication.Api.Models;

import org.szymon.publication.Domain.Model.Book;

public record BookResponse(
        Long id,
        String title,
        String author,
        Integer publicationYear,
        String publisher,
        String language,
        String category,
        String description,
        Integer pages,
        String isbn,
        String coverImageUrl,
        Integer availableCopies
) {

    public static BookResponse from(Book book, int availableCopies) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getPublisher(),
                book.getLanguage(),
                book.getCategory(),
                book.getDescription(),
                book.getPages(),
                book.getIsbn(),
                book.getCoverImageKey() == null
                        ? null
                        : "/api/publications/" + book.getId() + "/cover",
                availableCopies
        );
    }
}
