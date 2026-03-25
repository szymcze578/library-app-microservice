package org.szymon.publication.DataTransferObjects.Library;

public record LibraryData(
        long totalCount,
        long booksCount,
        long magazinesCount
) {
}
