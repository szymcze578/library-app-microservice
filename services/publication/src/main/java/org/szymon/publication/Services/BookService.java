package org.szymon.publication.Services;


import org.springframework.stereotype.Service;
import org.szymon.publication.DataTransferObjects.BookViewModel;
import org.szymon.publication.Domain.Model.Book;
import org.szymon.publication.Domain.Repositories.BookRepository;
import org.szymon.publication.Exceptions.PublicationAlreadyExistException;
import org.szymon.publication.Exceptions.PublicationNotFoundException;
import org.szymon.publication.Interfaces.IBookService;
import org.szymon.publication.Mappers.BookMapper;

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

    public List<BookViewModel> getBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::map).collect(Collectors.toList());
    }

    public BookViewModel getBookById(Long bookId){
        return bookRepository.findById(bookId)
                .map(bookMapper::map)
                .orElseThrow(
                        ()-> new PublicationNotFoundException("Book with this ID doesn't exist ID::" + bookId));
    }

    public Long addBook(BookViewModel request) {
        bookRepository.findByIsbn(request.isbn()).ifPresent(book -> {
            throw new PublicationAlreadyExistException("Book with ISBN already exists: " + request.isbn());
        });
        Book book = bookMapper.map(request);
        return bookRepository.save(book).getId();
    }

    public void deleteBook(Long id) {
        bookRepository.findById(id)
                .ifPresentOrElse(
                        book -> bookRepository.deleteById(book.getId()),
                        () -> {throw new PublicationNotFoundException("Book with this ID doesn't exist ID::" + id);}
                );
    }

    public BookViewModel updateBook(BookViewModel request) {
        var book = bookRepository.findById(request.id())
                .orElseThrow(
                        ()-> new PublicationNotFoundException("Book with this ID doesn't exist ID::" + request.id()));
        book = mergeBook(book, request);
        return bookMapper.map(bookRepository.save(book));
    }

    private Book mergeBook(Book book, BookViewModel request) {
        if(!request.title().isBlank()){
            book.setTitle(request.title());
        }
        if(!request.year().isBlank()){
            book.setYear(request.year());
        }
        if(!request.publisher().isBlank()){
            book.setPublisher(request.publisher());
        }
        if(!request.author().isBlank()){
            book.setAuthor(request.author());
        }
        if(!(request.pages() > 0) ){
            book.setPages(request.pages());
        }
        if(!request.isbn().isBlank()){
            book.setIsbn(request.isbn());
        }
        return book;
    }
}
