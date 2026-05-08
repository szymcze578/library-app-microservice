package org.szymon.publication.Api.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.szymon.publication.Api.Models.BookResponse;
import org.szymon.publication.Api.Models.CreateBookRequest;
import org.szymon.publication.Services.BookQueryService;
import org.szymon.publication.Services.BookService;


@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookQueryService bookQueryService;
    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<BookResponse> getByIsbn(@RequestParam String isbn) {
        BookResponse response = bookQueryService.getByIsbn(isbn);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid CreateBookRequest bookRequest) {
        BookResponse response = bookService.createBook(bookRequest);
        return ResponseEntity.ok(response);
    }
}
