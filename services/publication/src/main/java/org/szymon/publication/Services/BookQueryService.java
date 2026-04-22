package org.szymon.publication.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.szymon.publication.Api.Models.BookResponse;
import org.szymon.publication.Domain.Model.Book;
import org.szymon.publication.Domain.Enums.CatalogStatus;
import org.szymon.publication.Domain.Repositories.BookRepository;
import org.szymon.publication.Domain.Repositories.PublicationCatalogRepository;
import org.szymon.publication.Exceptions.BookNotFoundException;

@Service
@RequiredArgsConstructor
public class BookQueryService {

    private final BookRepository bookRepository;
    private final PublicationCatalogRepository catalogRepository;

    @Transactional(readOnly = true)
    public BookResponse getByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        int availableCopies = catalogRepository.countByPublicationIdAndStatus(
                book.getId(),
                CatalogStatus.AVAILABLE
        );

        return BookResponse.from(book, availableCopies);
    }

}
