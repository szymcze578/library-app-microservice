package org.szymon.publication.Interfaces;

import org.szymon.publication.DataTransferObjects.BookViewModel;

import java.util.List;

public interface IBookService {

    List<BookViewModel> getBooks();

    Long addBook(BookViewModel request);

    void deleteBook(Long id);

    BookViewModel updateBook(BookViewModel request);

    BookViewModel getBookById(Long bookId);
}
