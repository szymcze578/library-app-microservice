package org.example.project;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.szymon.publication.DataTransferObjects.BookViewModel;
import org.szymon.publication.Domain.Model.Book;
import org.szymon.publication.Domain.Repositories.BookRepository;
import org.szymon.publication.Exceptions.PublicationAlreadyExistException;
import org.szymon.publication.Exceptions.PublicationNotFoundException;
import org.szymon.publication.Mappers.BookMapper;
import org.szymon.publication.Services.BookService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    private BookViewModel bookViewModel;
    private Book book;

    @BeforeEach
    void setUp() {
        bookViewModel = new BookViewModel(1L, "Test Book", "2023", "Test Publisher",
                "Test Author", 200, "123-456-789");
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setYear("2023");
        book.setPublisher("Test Publisher");
        book.setAuthor("Test Author");
        book.setPages(200);
        book.setIsbn("123-456-789");
    }

    @Test
    void getBooks_ShouldReturnListOfBooks() {
        // Arrange
        when(bookRepository.findAll()).thenReturn(List.of(book));
        when(bookMapper.map(book)).thenReturn(bookViewModel);

        // Act
        List<BookViewModel> result = bookService.getBooks();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(bookViewModel, result.get(0));
        verify(bookRepository).findAll();
        verify(bookMapper).map(book);
    }

    @Test
    void getBookById_WhenBookExists_ShouldReturnBook() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.map(book)).thenReturn(bookViewModel);

        // Act
        BookViewModel result = bookService.getBookById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(bookViewModel, result);
        verify(bookRepository).findById(1L);
        verify(bookMapper).map(book);
    }

    @Test
    void getBookById_WhenBookDoesNotExist_ShouldThrowException() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PublicationNotFoundException.class, () -> bookService.getBookById(1L));
        verify(bookRepository).findById(1L);
        verifyNoInteractions(bookMapper);
    }

    @Test
    void addBook_WhenIsbnDoesNotExist_ShouldAddBook() {
        // Arrange
        when(bookRepository.findByIsbn(bookViewModel.isbn())).thenReturn(Optional.empty());
        when(bookMapper.map(bookViewModel)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);

        // Act
        Long result = bookService.addBook(bookViewModel);

        // Assert
        assertEquals(book.getId(), result);
        verify(bookRepository).findByIsbn(bookViewModel.isbn());
        verify(bookMapper).map(bookViewModel);
        verify(bookRepository).save(book);
    }

    @Test
    void addBook_WhenIsbnExists_ShouldThrowException() {
        // Arrange
        when(bookRepository.findByIsbn(bookViewModel.isbn())).thenReturn(Optional.of(book));

        // Act & Assert
        assertThrows(PublicationAlreadyExistException.class, () -> bookService.addBook(bookViewModel));
        verify(bookRepository).findByIsbn(bookViewModel.isbn());
        verifyNoInteractions(bookMapper);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void deleteBook_WhenBookExists_ShouldDeleteBook() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Act
        bookService.deleteBook(1L);

        // Assert
        verify(bookRepository).findById(1L);
        verify(bookRepository).deleteById(1L);
    }

    @Test
    void deleteBook_WhenBookDoesNotExist_ShouldThrowException() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PublicationNotFoundException.class, () -> bookService.deleteBook(1L));
        verify(bookRepository).findById(1L);
        verify(bookRepository, never()).deleteById(any());
    }

    @Test
    void updateBook_WhenBookExists_ShouldUpdateBook() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookMapper.map(book)).thenReturn(bookViewModel);

        // Act
        BookViewModel result = bookService.updateBook(bookViewModel);

        // Assert
        assertNotNull(result);
        assertEquals(bookViewModel, result);
        verify(bookRepository).findById(1L);
        verify(bookRepository).save(any(Book.class));
        verify(bookMapper).map(book);
    }

    @Test
    void updateBook_WhenBookDoesNotExist_ShouldThrowException() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PublicationNotFoundException.class, () -> bookService.updateBook(bookViewModel));
        verify(bookRepository).findById(1L);
        verify(bookRepository, never()).save(any());
        verifyNoInteractions(bookMapper);
    }

}
