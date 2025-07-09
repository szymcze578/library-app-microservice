package org.example.project.Mappers;

import org.example.project.Domain.Model.Book;
import org.example.project.DataTransferObjects.BookViewModel;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book map(BookViewModel request){
        return Book.builder()
                .title(request.title())
                .year(request.year())
                .publisher(request.publisher())
                .author(request.author())
                .pages(request.pages())
                .isbn(request.isbn())
                .build();
    }

    public BookViewModel map(Book book){
        return new BookViewModel(
                book.getId(),
                book.getTitle(),
                book.getYear(),
                book.getPublisher(),
                book.getAuthor(),
                book.getPages(),
                book.getIsbn());
    }
}
