package org.example.project;

import org.example.project.Exceptions.PublicationAlreadyExistException;
import org.example.project.Model.Book;
import org.example.project.Model.Magazine;
import org.example.project.Model.Publication;
import org.example.project.Model.PublicationDTO;
import org.example.project.Repositories.PublicationRepository;
import org.example.project.Services.PublicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PublicationServiceTest {

    @Mock
    private PublicationRepository publicationRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PublicationService publicationService;

    @BeforeEach
    public void setUp(){
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
}
