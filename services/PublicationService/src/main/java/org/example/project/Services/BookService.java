package org.example.project.Services;

import org.example.project.DataTransferObjects.BookDto;
import org.example.project.Domain.Model.Book;
import org.example.project.Domain.Repositories.BookRepository;
import org.example.project.Exceptions.PublicationAlreadyExistException;
import org.example.project.Exceptions.PublicationNotFoundException;
import org.example.project.Interfaces.IBookService;
import org.example.project.Mappers.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookDto).collect(Collectors.toList());
    }

    public Long addBook(BookDto request) {
        bookRepository.findByIsbn(request.isbn()).ifPresent(book -> {
            throw new PublicationAlreadyExistException("Book with ISBN already exists: " + request.isbn());
        });
        Book book = bookMapper.toBook(request);
        return bookRepository.save(book).getId();
    }

    public void deleteBook(Long id) {
        bookRepository.findById(id)
                .ifPresentOrElse(
                        book -> bookRepository.deleteById(book.getId()),
                        () -> {throw new PublicationNotFoundException("Book with this ID doesn't exist ID::" + id);}
                );
    }
}
