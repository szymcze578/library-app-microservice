package org.szymon.publication.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.szymon.publication.Domain.Enums.CatalogStatus;
import org.szymon.publication.Domain.Model.PublicationCatalog;

import java.util.Collection;
import java.util.List;

@Repository
public interface PublicationCatalogRepository extends JpaRepository<PublicationCatalog,Long> {

    int countByPublicationIdAndStatus(Long publicationId, CatalogStatus status);

    /**
     * Returns [publicationId, count] pairs for all given publications.
     * Avoids N+1: one query instead of one per publication.
     */
    @Query("""
            SELECT pc.publicationId, COUNT(pc)
            FROM PublicationCatalog pc
            WHERE pc.publicationId IN :publicationIds
              AND pc.status = :status
            GROUP BY pc.publicationId
            """)
    List<Object[]> countByPublicationIdsAndStatus(
            @Param("publicationIds") Collection<Long> publicationIds,
            @Param("status") CatalogStatus status
    );
}
