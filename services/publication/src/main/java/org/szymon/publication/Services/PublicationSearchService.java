package org.szymon.publication.Services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.szymon.publication.Api.Models.PublicationSearchResult;
import org.szymon.publication.Api.Models.PublicationsQuickSearchResponse;
import org.szymon.publication.Domain.Enums.CatalogStatus;
import org.szymon.publication.Domain.Enums.PublicationType;
import org.szymon.publication.Domain.Model.Book;
import org.szymon.publication.Domain.Model.Magazine;
import org.szymon.publication.Domain.Model.Publication;
import org.szymon.publication.Domain.Repositories.PublicationCatalogRepository;
import org.szymon.publication.Domain.Repositories.PublicationRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicationSearchService {

    private static final int DEFAULT_LIMIT = 5;
    private static final int MAX_LIMIT = 10;
    private static final int MIN_QUERY_LENGTH = 2;

    private final PublicationRepository publicationRepository;
    private final PublicationCatalogRepository catalogRepository;

    @Transactional(readOnly = true)
    public PublicationsQuickSearchResponse quickSearch(String query, Integer requestedLimit) {
        String normalized = query == null ? "" : query.trim();

        if (normalized.length() < MIN_QUERY_LENGTH) {
            return PublicationsQuickSearchResponse.empty();
        }

        // Get total count first. If zero, skip the page query entirely.
        long totalCount = publicationRepository.countSearchMatches(normalized);
        if (totalCount == 0) {
            return PublicationsQuickSearchResponse.empty();
        }

        int limit = clampLimit(requestedLimit);
        Pageable pageable = PageRequest.of(0, limit);

        List<Publication> matches = publicationRepository.search(normalized, pageable);
        Map<Long, Integer> availableCounts = loadAvailableCounts(matches);

        List<PublicationSearchResult> results = matches.stream()
                .map(pub -> toSearchResult(pub, availableCounts.getOrDefault(pub.getId(), 0)))
                .toList();

        return new PublicationsQuickSearchResponse(results, totalCount);
    }

    private Map<Long, Integer> loadAvailableCounts(List<Publication> publications) {
        List<Long> ids = publications.stream().map(Publication::getId).toList();
        return catalogRepository
                .countByPublicationIdsAndStatus(ids, CatalogStatus.AVAILABLE)
                .stream()
                .collect(Collectors.toMap(
                        row -> (Long) row[0],
                        row -> ((Long) row[1]).intValue()
                ));
    }

    private PublicationSearchResult toSearchResult(Publication pub, int availableCopies) {
        if (pub instanceof Book book) {
            return new PublicationSearchResult(
                    book.getId(), PublicationType.BOOK, book.getTitle(),
                    book.getAuthor(), book.getPublisher(), book.getPublicationYear(),
                    book.getIsbn(), null, availableCopies
            );
        }
        if (pub instanceof Magazine magazine) {
            return new PublicationSearchResult(
                    magazine.getId(), PublicationType.MAGAZINE, magazine.getTitle(),
                    null, magazine.getPublisher(), magazine.getPublicationYear(),
                    null, magazine.getIssn(), availableCopies
            );
        }
        throw new IllegalStateException("Unknown publication type: " + pub.getClass());
    }

    private int clampLimit(Integer requested) {
        if (requested == null || requested < 1) return DEFAULT_LIMIT;
        return Math.min(requested, MAX_LIMIT);
    }
}
