package org.example.project.Controllers;

import org.example.project.DataTransferObjects.Library.LibraryViewModel;
import org.example.project.Interfaces.ILibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
