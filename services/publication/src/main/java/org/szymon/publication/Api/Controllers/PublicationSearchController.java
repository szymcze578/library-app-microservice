package org.szymon.publication.Api.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.szymon.publication.Api.Models.PublicationsQuickSearchResponse;
import org.szymon.publication.Services.PublicationSearchService;

@RestController
@RequestMapping("/api/v1/publications")
@AllArgsConstructor
public class PublicationSearchController {

    private final PublicationSearchService searchService;
    /**
     * Quick search for the header popover. Returns a capped list of results
     * plus the total match count so the UI can show "5 of 47 results" with
     * a link to a full search page.
     *
     * @param q     search query (min 2 characters; shorter returns empty)
     * @param limit optional; defaults to 5, capped at 10
     */
    @GetMapping("/search/quick")
    public ResponseEntity<PublicationsQuickSearchResponse> quickSearch(
            @RequestParam("q") String q,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return ResponseEntity.ok(searchService.quickSearch(q, limit));
    }

}
