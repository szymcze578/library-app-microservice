package org.example.project.Controllers;

import jakarta.validation.Valid;
import org.example.project.DataTransferObjects.BookDto;
import org.example.project.Domain.Model.Book;
import org.example.project.Services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getBooks")
    public ResponseEntity<List<BookDto>> getBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }

    @PostMapping("/addBook")
    public ResponseEntity<Long> addBook(@RequestBody @Valid BookDto request){
        return ResponseEntity.ok(bookService.addBook(request));
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<?> deleteBook(@RequestParam("book-id") Long id){
        bookService.deleteBook(id);
        return ResponseEntity.accepted().build();
    }

}
