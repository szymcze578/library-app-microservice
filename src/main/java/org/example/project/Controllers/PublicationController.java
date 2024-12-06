package org.example.project.Controllers;

import org.example.project.Model.Publication;
import org.example.project.Repositories.PublicationRepository;
import org.example.project.Services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PublicationController {

    @Autowired
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

//    @PostMapping("/addPublication")
//    public ResponseEntity<String> addPublication(@RequestBody Publication publication){
//        try {
//            publicationService.addPublication(publication);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added publication " + publication.getTitle());
//        }catch (IllegalStateException e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
//
//    @GetMapping("/findByAuthor")
//    public ResponseEntity<List<Publication>> findByAuthor(@RequestParam String author){
//        List<Publication> publications = publicationService.findPublicationByAuthor(author);
//
//        if(publications == null || publications.isEmpty())
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//        return ResponseEntity.ok(publications);
//    }

}
