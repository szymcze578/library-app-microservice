package org.example.project.Services;

import org.example.project.Model.Publication;
import org.example.project.Repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

    @Autowired
    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public void addPublication(Publication publication){
        Optional<Publication> foundPublication= publicationRepository.findPublicationByName(publication.getTitle());

        if(foundPublication.isPresent()){
            throw new IllegalStateException("Publication " +publication.getTitle()+ " already exists.");
        }
        publicationRepository.save(publication);
    }

    public List<Publication> findPublicationByAuthor(String author){
        return publicationRepository.findPublicationsByAuthor(author);
    }

}
