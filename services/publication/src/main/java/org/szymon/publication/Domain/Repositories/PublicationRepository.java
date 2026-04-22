package org.szymon.publication.Domain.Repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.szymon.publication.Domain.Model.Publication;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {

    String SEARCH_WHERE = """
            LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%'))
            OR (TYPE(p) = org.szymon.publication.Domain.Model.Book
                AND LOWER(TREAT(p AS org.szymon.publication.Domain.Model.Book).author)
                    LIKE LOWER(CONCAT('%', :query, '%')))
            OR LOWER(p.publisher) LIKE LOWER(CONCAT('%', :query, '%'))
            """;

    @Query("SELECT p FROM Publication p WHERE " + SEARCH_WHERE + " ORDER BY p.title ASC")
    List<Publication> search(@Param("query") String query, Pageable pageable);

    @Query("SELECT COUNT(p) FROM Publication p WHERE " + SEARCH_WHERE)
    long countSearchMatches(@Param("query") String query);
}
