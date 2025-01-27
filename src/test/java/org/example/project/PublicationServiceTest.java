package org.example.project;

import org.example.project.Model.Book;
import org.example.project.Model.Publication;
import org.example.project.Model.PublicationDTO;
import org.example.project.Repositories.PublicationRepository;
import org.example.project.Services.PublicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
                .title("The Hobbit")
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

}
