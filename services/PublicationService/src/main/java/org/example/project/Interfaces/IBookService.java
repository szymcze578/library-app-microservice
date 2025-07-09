package org.example.project.Interfaces;

import jakarta.validation.Valid;
import org.example.project.DataTransferObjects.BookViewModel;

import java.util.List;

public interface IBookService {

    List<BookViewModel> getBooks();

    Long addBook(BookViewModel request);

    void deleteBook(Long id);

    BookViewModel updateBook(BookViewModel request);

    BookViewModel getBookById(Long bookId);
}
