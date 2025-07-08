package org.example.project.Interfaces;

import org.example.project.Model.PublicationDTO;

import java.util.List;

public interface IPublicationService {

    List<PublicationDTO> getAllPublications();
    PublicationDTO addPublication(PublicationDTO publicationDTO);

    List<PublicationDTO> getBooks();
    List<PublicationDTO> getMagazines();
    PublicationDTO updatePublication(long id, PublicationDTO publication);
}
