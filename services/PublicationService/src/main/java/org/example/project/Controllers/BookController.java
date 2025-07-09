package org.example.project.Controllers;

import jakarta.validation.Valid;
import org.example.project.Interfaces.IBookService;
import org.example.project.DataTransferObjects.BookViewModel;
import org.example.project.Services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
public class BookController {

    private final IBookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getBooks")
    public ResponseEntity<List<BookViewModel>> getBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<BookViewModel> getBookById(@PathVariable("book-id") Long bookId){
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PostMapping("/addBook")
    public ResponseEntity<Long> addBook(@RequestBody @Valid BookViewModel request){
        return ResponseEntity.ok(bookService.addBook(request));
    }

    @DeleteMapping("/{book-id}")
    public ResponseEntity<?> deleteBook(@PathVariable("book-id") Long id){
        bookService.deleteBook(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/updateBook")
    public ResponseEntity<BookViewModel> updateBook(@RequestBody @Valid BookViewModel request){
        return ResponseEntity.ok(bookService.updateBook(request));
    }

}
