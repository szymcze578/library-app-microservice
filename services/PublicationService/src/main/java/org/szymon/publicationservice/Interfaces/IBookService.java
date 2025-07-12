package org.szymon.publicationservice.Interfaces;

import org.szymon.publicationservice.DataTransferObjects.BookViewModel;

import java.util.List;

public interface IBookService {

    List<BookViewModel> getBooks();

    Long addBook(BookViewModel request);

    void deleteBook(Long id);

    BookViewModel updateBook(BookViewModel request);

    BookViewModel getBookById(Long bookId);
}
