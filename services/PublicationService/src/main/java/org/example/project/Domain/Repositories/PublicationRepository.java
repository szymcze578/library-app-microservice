package org.example.project.Domain.Repositories;

import org.example.project.Domain.Model.Book;
import org.example.project.Domain.Model.Magazine;
import org.example.project.Domain.Model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {

    //List<Publication> findPublicationsByAuthor(String author);

    Optional<Publication> findPublicationByTitle(String name);

    @Query("SELECT b FROM Book b")
    List<Book> findBooks();

    @Query("SELECT m FROM Magazine m")
    List<Magazine> findMagazines();

    void delete(Publication publication);

    Optional<Publication> findPublicationById(long id);
}
