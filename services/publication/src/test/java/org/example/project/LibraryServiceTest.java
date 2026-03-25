package org.example.project;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.szymon.publication.DataTransferObjects.BookViewModel;
import org.szymon.publication.DataTransferObjects.Library.LibraryViewModel;
import org.szymon.publication.DataTransferObjects.MagazineViewModel;
import org.szymon.publication.Domain.Repositories.BookRepository;
import org.szymon.publication.Interfaces.IBookService;
import org.szymon.publication.Interfaces.IMagazineService;
import org.szymon.publication.Services.LibraryService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private IBookService bookService;

    @Mock
    private IMagazineService magazineService;

    @InjectMocks
    private LibraryService libraryService;

    private BookViewModel bookViewModel;
    private MagazineViewModel magazineViewModel;
    private List<BookViewModel> books;
    private List<MagazineViewModel> magazines;

    @BeforeEach
    void setUp() {
        bookViewModel = new BookViewModel(1L, "Test Book", "2023", "Test Publisher",
                "Test Author", 200, "123-456-789");
        magazineViewModel = new MagazineViewModel(1L, "Test Magazine", "2023",
                "Test Publisher", "01-15", "English");

        books = List.of(bookViewModel);
        magazines = List.of(magazineViewModel);
    }

    @Test
    void getAllPublications_WhenTypeIsNull_ShouldReturnAllPublications() {
        //Arrange
        when(bookService.getBooks()).thenReturn(books);
        when(magazineService.getAllMagazines()).thenReturn(magazines);

        // Act
        LibraryViewModel result = libraryService.getAllPublications(null, false);

        // Assert
        assertNotNull(result);
        assertEquals(books, result.books());
        assertEquals(magazines, result.magazines());
        assertNull(result.stats());
        verify(bookService).getBooks();
        verify(magazineService).getAllMagazines();
    }

    @Test
    void getAllPublications_WhenTypeIsBook_ShouldReturnOnlyBooks() {
        //Arrange
        when(bookService.getBooks()).thenReturn(books);

        // Act
        LibraryViewModel result = libraryService.getAllPublications("book", false);

        // Assert
        assertNotNull(result);
        assertEquals(books, result.books());
        assertTrue(result.magazines().isEmpty());
        assertNull(result.stats());
        verify(bookService).getBooks();
        verify(magazineService, never()).getAllMagazines();
    }

    @Test
    void getAllPublications_WhenTypeIsMagazine_ShouldReturnOnlyMagazines() {
        //Arrange
        when(magazineService.getAllMagazines()).thenReturn(magazines);

        // Act
        LibraryViewModel result = libraryService.getAllPublications("magazine", false);

        // Assert
        assertNotNull(result);
        assertTrue(result.books().isEmpty());
        assertEquals(magazines, result.magazines());
        assertNull(result.stats());
        verify(bookService, never()).getBooks();
        verify(magazineService).getAllMagazines();
    }

    @Test
    void getAllPublications_WhenIncludeStatsIsTrue_ShouldIncludeStats() {
        //Arrange
        when(bookService.getBooks()).thenReturn(books);
        when(magazineService.getAllMagazines()).thenReturn(magazines);

        // Act
        LibraryViewModel result = libraryService.getAllPublications(null, true);

        // Assert
        assertNotNull(result);
        assertEquals(books, result.books());
        assertEquals(magazines, result.magazines());
        assertNotNull(result.stats());
        assertEquals(2, result.stats().totalCount());
        assertEquals(1, result.stats().booksCount());
        assertEquals(1, result.stats().magazinesCount());
        verify(bookService).getBooks();
        verify(magazineService).getAllMagazines();
    }

    @Test
    void getAllPublications_WhenTypeIsBookAndIncludeStatsIsTrue_ShouldIncludeStatsForBooksOnly() {
        //Arrange
        when(bookService.getBooks()).thenReturn(books);

        // Act
        LibraryViewModel result = libraryService.getAllPublications("book", true);

        // Assert
        assertNotNull(result);
        assertEquals(books, result.books());
        assertTrue(result.magazines().isEmpty());
        assertNotNull(result.stats());
        assertEquals(1, result.stats().totalCount());
        assertEquals(1, result.stats().booksCount());
        assertEquals(0, result.stats().magazinesCount());
        verify(bookService).getBooks();
        verify(magazineService, never()).getAllMagazines();
    }

    @Test
    void getAllPublications_WhenTypeIsMagazineAndIncludeStatsIsTrue_ShouldIncludeStatsForMagazinesOnly() {
        //Arrange
        when(magazineService.getAllMagazines()).thenReturn(magazines);

        // Act
        LibraryViewModel result = libraryService.getAllPublications("magazine", true);

        // Assert
        assertNotNull(result);
        assertTrue(result.books().isEmpty());
        assertEquals(magazines, result.magazines());
        assertNotNull(result.stats());
        assertEquals(1, result.stats().totalCount());
        assertEquals(0, result.stats().booksCount());
        assertEquals(1, result.stats().magazinesCount());
        verify(bookService, never()).getBooks();
        verify(magazineService).getAllMagazines();
    }

    @Test
    void getAllPublications_WhenTypeIsInvalid_ShouldReturnEmptyLists() {
        // Act
        LibraryViewModel result = libraryService.getAllPublications("invalid", false);

        // Assert
        assertNotNull(result);
        assertTrue(result.books().isEmpty());
        assertTrue(result.magazines().isEmpty());
        assertNull(result.stats());
        verify(bookService, never()).getBooks();
        verify(magazineService, never()).getAllMagazines();
    }


}
