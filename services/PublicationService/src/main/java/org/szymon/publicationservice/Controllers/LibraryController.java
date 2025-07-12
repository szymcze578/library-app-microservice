package org.szymon.publicationservice.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.szymon.publicationservice.DataTransferObjects.Library.LibraryViewModel;
import org.szymon.publicationservice.Interfaces.ILibraryService;

@RequestMapping("/api/v1/library")
@RestController
public class LibraryController {

    @Autowired
    private final ILibraryService libraryService;

    public LibraryController(ILibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<LibraryViewModel> getAllPublications(
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "false") boolean includeStats
    ) {
        return ResponseEntity.ok(libraryService.getAllPublications(type, includeStats));
    }
}
