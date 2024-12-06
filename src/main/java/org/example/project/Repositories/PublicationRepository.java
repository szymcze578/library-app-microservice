package org.example.project.Repositories;

import org.example.project.Model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {

    List<Publication> findPublicationsByAuthor(String author);

    Optional<Publication> findPublicationByName(String name);

}
