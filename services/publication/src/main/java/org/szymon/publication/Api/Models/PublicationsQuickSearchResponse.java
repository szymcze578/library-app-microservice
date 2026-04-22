package org.szymon.publication.Api.Models;

import java.util.List;

public record PublicationsQuickSearchResponse(
        List<PublicationSearchResult> results,
        long totalCount
) {

    public static PublicationsQuickSearchResponse empty() {
        return new PublicationsQuickSearchResponse(List.of(), 0);
    }
}
