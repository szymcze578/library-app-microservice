package org.szymon.publication.Api.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.szymon.publication.Api.Models.BookResponse;
import org.szymon.publication.Services.BookQueryService;


@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookQueryService bookQueryService;

    @GetMapping()
    public ResponseEntity<BookResponse> getByIsbn(@RequestParam String isbn) {
        BookResponse response = bookQueryService.getByIsbn(isbn);
        return ResponseEntity.ok(response);
    }
}
