package org.example.project.Mappers;

import org.example.project.DataTransferObjects.BookDto;
import org.example.project.Domain.Model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book toBook(BookDto request){
        return Book.builder()
                .title(request.title())
                .year(request.year())
                .publisher(request.publisher())
                .author(request.author())
                .pages(request.pages())
                .isbn(request.isbn())
                .build();
    }

    public BookDto toBookDto(Book book){
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getYear(),
                book.getPublisher(),
                book.getAuthor(),
                book.getPages(),
                book.getIsbn());
    }
}
