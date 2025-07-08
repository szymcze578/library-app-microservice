package org.example.project.Interfaces;

import org.example.project.DataTransferObjects.BookDto;

import java.util.List;

public interface IBookService {

    List<BookDto> getBooks();
    Long addBook(BookDto request);
    void deleteBook(Long id);
}
