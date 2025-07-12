package org.szymon.publicationservice.DataTransferObjects.Library;

public record LibraryData(
        long totalCount,
        long booksCount,
        long magazinesCount
) {
}
