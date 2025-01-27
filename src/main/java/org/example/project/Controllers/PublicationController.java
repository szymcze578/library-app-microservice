package org.example.project.Controllers;

import org.example.project.Model.Book;
import org.example.project.Model.Magazine;
import org.example.project.Model.Publication;
import org.example.project.Model.PublicationDTO;
import org.example.project.Repositories.PublicationRepository;
import org.example.project.Services.PublicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/publications")
@RestController
public class PublicationController {

    @Autowired
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping("/addPublication")
    public ResponseEntity<PublicationDTO> addPublication(@RequestBody PublicationDTO publication) {
        PublicationDTO result = publicationService.addPublication(publication);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/getPublications")
    public ResponseEntity<List<PublicationDTO>> getPublications() {
        List<PublicationDTO> publications = publicationService.getAllPublications();
        return ResponseEntity.status(HttpStatus.OK).body(publications);
    }

    @GetMapping("/getBooks")
    public ResponseEntity<List<PublicationDTO>> getBooks(){
        List<PublicationDTO> books = publicationService.getBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/getMagazines")
    public ResponseEntity<List<PublicationDTO>> getMagazines(){
        List<PublicationDTO> magazines = publicationService.getMagazines();
        return ResponseEntity.status(HttpStatus.OK).body(magazines);
    }

    @DeleteMapping("/deleteByTitle")
    public ResponseEntity deletePublication(@RequestParam String title) {
        try{
            publicationService.deletePublication(title);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted publication " + title);
    }

    @PutMapping("/updatePublication")
    public ResponseEntity<PublicationDTO> updatePublication(@RequestParam long id, @RequestBody PublicationDTO publication) {
        try {
            PublicationDTO updatedPublication = publicationService.updatePublication(id, publication);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPublication);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

//    @PostMapping("/addPublication")
//    public ResponseEntity<String> addPublication(@RequestBody Publication publication){
//        try {
//            publicationService.addPublication(publication);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added publication " + publication.getTitle());
//        }catch (IllegalStateException e){
//
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
