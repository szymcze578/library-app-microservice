package org.szymon.publication.Services;

import jakarta.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.szymon.publication.Api.Models.BookResponse;
import org.szymon.publication.Api.Models.CreateBookRequest;
import org.szymon.publication.Domain.Model.Book;
import org.szymon.publication.Domain.Repositories.BookRepository;
import org.szymon.publication.Exceptions.BookNotFoundException;
import org.szymon.publication.Exceptions.DuplicateIsbnException;

import java.time.Year;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookResponse createBook(CreateBookRequest request) {
        String normalizedIsbn = normalizeIsbn(request.isbn());

        if (bookRepository.existsByIsbn(normalizedIsbn)){
            throw new DuplicateIsbnException(normalizedIsbn);
        };

        Book book = Book.builder()
                .title(request.title().trim())
                .author(request.author().trim())
                .publicationYear(request.publicationYear())
                .publisher(request.publisher().trim())
                .language(request.language().trim().toLowerCase())
                .pages(request.pages())
                .isbn(normalizedIsbn)
                .category(request.category() == null ? null : request.category().trim())
                .description(request.description())
                .build();

        try {
            Book saved = bookRepository.save(book);
            return BookResponse.from(saved, 0);

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateIsbnException(normalizedIsbn);
        }
    }

    /**
     * Strip whitespace, dashes, and uppercase the final 'X' for ISBN-10.
     * Stored form is normalized; user can send "978-0-451-52493-5" or "9780451524935".
     */
    private String normalizeIsbn(String raw) {
        if (raw == null) return null;
        return raw.replaceAll("[\\s-]", "").toUpperCase();
    }
}
