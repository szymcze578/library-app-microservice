package org.example.project.Services;

import org.example.project.Enums.PublicationType;
import org.example.project.Exceptions.PublicationAlreadyExistException;
import org.example.project.Exceptions.PublicationNotFoundException;
import org.example.project.Domain.Model.Book;
import org.example.project.Domain.Model.Magazine;
import org.example.project.Domain.Model.Publication;
import org.example.project.Model.PublicationDTO;
import org.example.project.Domain.Repositories.PublicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final ModelMapper modelMapper;

    public PublicationService(PublicationRepository publicationRepository, ModelMapper modelMapper) {
        this.publicationRepository = publicationRepository;
        this.modelMapper = modelMapper;
    }

    public PublicationDTO addPublication(PublicationDTO publicationDTO) {
        if (publicationExist(publicationDTO)){
           throw new PublicationAlreadyExistException("Publication already exist " + publicationDTO.getTitle());
        }
        Publication publication = switch (PublicationType.fromValue(publicationDTO.getType())) {
            case BOOK -> modelMapper.map(publicationDTO, Book.class);
            case MAGAZINE -> modelMapper.map(publicationDTO, Magazine.class);
        };
        return modelMapper.map(publicationRepository.save(publication), PublicationDTO.class);
    }

    private boolean publicationExist(PublicationDTO publication) {
        return publicationRepository.findPublicationByTitle(publication.getTitle()).isPresent();
    }

    public List<PublicationDTO> getAllPublications() {
        List<Publication> publications = publicationRepository.findAll();
        return publications.stream()
                .map(publication -> {
                    PublicationDTO result = modelMapper.map(publication, PublicationDTO.class);
                    if(publication instanceof Magazine){
                        result.setType("Magazine");
                    }else if(publication instanceof Book){
                        result.setType("Book");
                    }
                    return result;
                })
                .collect(Collectors.toList());
    }

    public List<PublicationDTO> getBooks() {
        List<Book> publications = publicationRepository.findBooks();

        return publications.stream()
                .map(publication -> {
                    PublicationDTO dto = modelMapper.map(publication, PublicationDTO.class);
                    dto.setType(PublicationType.BOOK.getValue());
                    return dto;
                }).collect(Collectors.toList());
    }

    public List<PublicationDTO> getMagazines() {
        List<Magazine> magazines = publicationRepository.findMagazines();

        return magazines.stream()
                .map(magazine -> {
                    PublicationDTO result = modelMapper.map(magazine, PublicationDTO.class);
                    result.setType(PublicationType.MAGAZINE.getValue());
                    return result;
                }).collect(Collectors.toList());
    }

    public void deletePublication(String title) {
        publicationRepository.findPublicationByTitle(title).ifPresentOrElse(
                publicationRepository::delete,
                () ->{throw new PublicationNotFoundException("Publication not found: " + title);}
        );
    }

    public PublicationDTO updatePublication(long id, PublicationDTO publication) {
        Publication foundPublication = publicationRepository.findPublicationById(id)
                .orElseThrow(() -> new PublicationNotFoundException("Publication not found: " + publication.getTitle()));

        modelMapper.map(publication, foundPublication);

        publicationRepository.save(foundPublication);
        return modelMapper.map(foundPublication, PublicationDTO.class);
    }

//    public void addPublication(Publication publication){
//        Optional<Publication> foundPublication= publicationRepository.findPublicationByName(publication.getTitle());
//
//        if(foundPublication.isPresent()){
//            throw new IllegalStateException("Publication " +publication.getTitle()+ " already exists.");
//        }
//        publicationRepository.save(publication);
//    }
//
//    public List<Publication> findPublicationByAuthor(String author){
//        return publicationRepository.findPublicationsByAuthor(author);
//    }

}
