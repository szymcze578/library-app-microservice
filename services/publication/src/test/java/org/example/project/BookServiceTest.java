package org.example.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.szymon.publication.Api.Models.BookResponse;
import org.szymon.publication.Api.Models.CreateBookRequest;
import org.szymon.publication.Domain.Model.Book;
import org.szymon.publication.Domain.Repositories.BookRepository;
import org.szymon.publication.Services.BookService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Test
    void createBook_persistsAndReturnsResponse() {
        var request = new CreateBookRequest(
                "The Hobbit", 1997,"George Allen & Unwin", "EN","Fantasy",
                "Description", "J.R.R. Tolkien", 310, "9780547928227"
        );

        when(bookRepository.save(any(Book.class))).thenAnswer(inv -> {
            Book b = inv.getArgument(0);
            ReflectionTestUtils.setField(b, "id", 1L);
            return b;
        });

        BookResponse response = bookService.createBook(request);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.isbn()).isEqualTo("9780547928227");
        assertThat(response.availableCopies()).isZero();
    }
}
