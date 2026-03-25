package org.szymon.publication.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.szymon.publication.Domain.Model.Book;
import org.szymon.publication.Domain.Model.Magazine;
import org.szymon.publication.Domain.Model.Publication;

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
