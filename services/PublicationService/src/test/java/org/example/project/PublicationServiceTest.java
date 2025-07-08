package org.example.project;


import org.example.project.Domain.Model.Book;
import org.example.project.Domain.Model.Magazine;
import org.example.project.Domain.Model.Publication;
import org.example.project.Domain.Repositories.PublicationRepository;
import org.example.project.Exceptions.PublicationAlreadyExistException;
import org.example.project.Model.PublicationDTO;
import org.example.project.Services.PublicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PublicationServiceTest {

    @Mock
    private PublicationRepository publicationRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PublicationService publicationService;

    private Publication book;
    private Publication magazine;


    @BeforeEach
    public void setUp(){
        book = new Book("The Hobbit 2",
                "1937",
                "George Allen & Unwin",
                "J.R.R. Tolkien",
                310,
                "978-0547928227");

        magazine = new Magazine(
                "F1 esports",
                "2023",
                "F1 limited",
                "12.10",
                "PL");
        MockitoAnnotations.openMocks(this);
    }

    //Add publication test scenarios:
    // -> publication already exists
    // -> add publication as Book
    // -> add publication as Magazine
    // -> constraint validation exception

    @Test
    public void testAddPublication_WhenTypeIsBook_ShouldSaveBook(){
        PublicationDTO publicationDTO = PublicationDTO.builder()
                .type("Book")
                .title("The Hobbit 2")
                .year("1937")
                .publisher("George Allen & Unwin")
                .author("J.R.R. Tolkien")
                .pages(310)
                .isbn("978-0547928227")
                .build();

        Book mappedBook = new Book();
        // Mock ModelMapper behavior
        when(modelMapper.map(publicationDTO, Book.class)).thenReturn(mappedBook);

        // Act
        publicationService.addPublication(publicationDTO);

        // Assert
        verify(publicationRepository).save(mappedBook);
    }

    @Test
    public void testAddPublication_whenTypeIsMagazine_shouldSaveMagazine(){
        PublicationDTO publicationDTO = PublicationDTO.builder()
                .type("Magazine")
                .title("F1 esports")
                .year("2023")
                .publisher("F1 limited")
                .monthDay("12.10")
                .language("PL").build();

        Magazine mappedMagazine = new Magazine();

        when(modelMapper.map(publicationDTO, Magazine.class)).thenReturn(mappedMagazine);
        PublicationDTO savedPublication = publicationService.addPublication(publicationDTO);

        //assert
        verify(publicationRepository).save(mappedMagazine);
    }

    @Test
    public void testAddPublication_whenPublicationExists_shouldThrowError(){
        PublicationDTO publicationDTO = PublicationDTO.builder()
                .type("Book")
                .title("The Hobbit 2")
                .year("1937")
                .publisher("George Allen & Unwin")
                .author("J.R.R. Tolkien")
                .pages(310)
                .isbn("978-0547928227")
                .build();

        Book mappedBook = new Book();
        // Mock repository behavior to simulate that the publication already exists
        when(publicationRepository.findPublicationByTitle("The Hobbit 2"))
                .thenReturn(Optional.of(mappedBook));

        // Act & Assert
        assertThrows(PublicationAlreadyExistException.class, () -> publicationService.addPublication(publicationDTO));

        // Verify that the save method was NOT called
        verify(publicationRepository, never()).save(any());
    }

    @Test
    public void testGetPublications(){

        // Mock DTOs
        PublicationDTO bookDTO = new PublicationDTO();
        bookDTO.setTitle("The Hobbit 2");
        bookDTO.setType("Book");
        PublicationDTO magazineDTO = new PublicationDTO();
        magazineDTO.setTitle("F1 esports");
        magazineDTO.setType("Magazine");

        // Mock ModelMapper behavior
        when(modelMapper.map(book, PublicationDTO.class)).thenReturn(bookDTO);
        when(modelMapper.map(magazine, PublicationDTO.class)).thenReturn(magazineDTO);

        // Arrange
        List<Publication> publications = List.of(book, magazine);
        when(publicationRepository.findAll()).thenReturn(publications);
        System.out.println(publications);

        // Act
        List<PublicationDTO> result = publicationService.getAllPublications();
        System.out.println(result);

        // Assert
        assertEquals(2, result.size());

        // Check first publication (Book)
        assertEquals("The Hobbit 2", result.get(0).getTitle());
        assertEquals("Book", result.get(0).getType());

        // Check second publication (Magazine)
        assertEquals("F1 esports", result.get(1).getTitle());
        assertEquals("Magazine", result.get(1).getType());

        // Verify repository interaction
        verify(publicationRepository, times(1)).findAll();
    }

    @Test
    public void testGetBooks(){
        PublicationDTO bookDTO = new PublicationDTO();
        bookDTO.setTitle("The Hobbit 2");
        bookDTO.setType("Book");

        // Mock ModelMapper behavior
        when(modelMapper.map(book, PublicationDTO.class)).thenReturn(bookDTO);

        // Arrange
        List<Book> books = List.of((Book)book);
        when(publicationRepository.findBooks()).thenReturn(books);

        // Act
        List<PublicationDTO> result = publicationService.getBooks();

        assertEquals(1, result.size());
        assertEquals("The Hobbit 2", result.get(0).getTitle());
        assertEquals("Book", result.get(0).getType());

        verify(publicationRepository, times(1)).findBooks();
    }

    @Test
    public void testGetMagazines(){
        PublicationDTO magazineDTO = new PublicationDTO();
        magazineDTO.setTitle("F1 esports");
        magazineDTO.setType("Magazine");

        when(modelMapper.map(magazine, PublicationDTO.class)).thenReturn(magazineDTO);
        List<Magazine> magazines = List.of((Magazine) magazine);
        when(publicationRepository.findMagazines()).thenReturn(magazines);

        List<PublicationDTO> result = publicationService.getMagazines();

        assertEquals(1, result.size());
        assertEquals("F1 esports", result.get(0).getTitle());
        assertEquals("Magazine", result.get(0).getType());

        verify(publicationRepository, times(1)).findMagazines();
    }

}
