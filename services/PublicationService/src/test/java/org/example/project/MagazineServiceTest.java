package org.example.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.szymon.publicationservice.DataTransferObjects.MagazineViewModel;
import org.szymon.publicationservice.Domain.Model.Magazine;
import org.szymon.publicationservice.Domain.Repositories.MagazineRepository;
import org.szymon.publicationservice.Exceptions.PublicationNotFoundException;
import org.szymon.publicationservice.Mappers.MagazineMapper;
import org.szymon.publicationservice.Services.MagazineService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MagazineServiceTest {

    @Mock
    private MagazineRepository magazineRepository;

    @Mock
    private MagazineMapper magazineMapper;

    @InjectMocks
    private MagazineService magazineService;

    private MagazineViewModel magazineViewModel;
    private Magazine magazine;

    @BeforeEach
    void setUp() {
        magazineViewModel = new MagazineViewModel(1L, "Test Magazine", "2023",
                "Test Publisher", "01-15", "English");

        magazine = new Magazine();
        magazine.setId(1L);
        magazine.setTitle("Test Magazine");
        magazine.setYear("2023");
        magazine.setPublisher("Test Publisher");
        magazine.setMonthDay("01-15");
        magazine.setLanguage("English");
    }

    @Test
    void getAllMagazines_ShouldReturnListOfMagazines() {
        // Arrange
        when(magazineRepository.findAll()).thenReturn(List.of(magazine));
        when(magazineMapper.map(magazine)).thenReturn(magazineViewModel);

        // Act
        List<MagazineViewModel> result = magazineService.getAllMagazines();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(magazineViewModel, result.get(0));
        verify(magazineRepository).findAll();
        verify(magazineMapper).map(magazine);
    }

    @Test
    void addMagazine_ShouldSaveMagazine() {
        // Arrange
        when(magazineMapper.map(magazineViewModel)).thenReturn(magazine);
        when(magazineRepository.save(magazine)).thenReturn(magazine);

        // Act
        Long result = magazineService.addMagazine(magazineViewModel);

        // Assert
        verify(magazineMapper).map(magazineViewModel);
        verify(magazineRepository).save(magazine);
        assertNull(result); // Current implementation returns null
    }

    @Test
    void deleteMagazine_WhenMagazineExists_ShouldDeleteMagazine() {
        // Arrange
        when(magazineRepository.findById(1L)).thenReturn(Optional.of(magazine));

        // Act
        magazineService.deleteMagazine(1L);

        // Assert
        verify(magazineRepository).findById(1L);
        verify(magazineRepository).deleteById(1L);
    }

    @Test
    void deleteMagazine_WhenMagazineDoesNotExist_ShouldThrowException() {
        // Arrange
        when(magazineRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PublicationNotFoundException.class,
                () -> magazineService.deleteMagazine(1L));
        verify(magazineRepository).findById(1L);
        verify(magazineRepository, never()).deleteById(any());
    }

    @Test
    void updateMagazine_WhenMagazineExists_ShouldUpdateMagazine() {
        // Arrange
        when(magazineRepository.findById(1L)).thenReturn(Optional.of(magazine));
        when(magazineRepository.save(any(Magazine.class))).thenReturn(magazine);
        when(magazineMapper.map(magazine)).thenReturn(magazineViewModel);

        // Act
        MagazineViewModel result = magazineService.updateMagazine(magazineViewModel);

        // Assert
        assertNotNull(result);
        assertEquals(magazineViewModel, result);
        verify(magazineRepository).findById(1L);
        verify(magazineRepository).save(any(Magazine.class));
        verify(magazineMapper).map(magazine);
    }

    @Test
    void updateMagazine_WhenMagazineDoesNotExist_ShouldThrowException() {
        // Arrange
        when(magazineRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PublicationNotFoundException.class,
                () -> magazineService.updateMagazine(magazineViewModel));
        verify(magazineRepository).findById(1L);
        verify(magazineRepository, never()).save(any());
        verifyNoInteractions(magazineMapper);
    }

    @Test
    void updateMagazine_ShouldUpdateOnlyNonBlankFields() {
        // Arrange
        when(magazineRepository.findById(1L)).thenReturn(Optional.of(magazine));
        when(magazineRepository.save(any(Magazine.class))).thenReturn(magazine);
        when(magazineMapper.map(magazine)).thenReturn(magazineViewModel);

        MagazineViewModel partialUpdate = new MagazineViewModel(1L, "", "",
                "New Publisher", "", "New Language");

        // Act
        magazineService.updateMagazine(partialUpdate);

        // Assert
        verify(magazineRepository).save(argThat(savedMagazine -> {
            assertEquals("Test Magazine", savedMagazine.getTitle()); // unchanged
            assertEquals("2023", savedMagazine.getYear()); // unchanged
            assertEquals("New Publisher", savedMagazine.getPublisher()); // changed
            assertEquals("01-15", savedMagazine.getMonthDay()); // unchanged
            assertEquals("New Language", savedMagazine.getLanguage()); // changed
            return true;
        }));
    }

}
