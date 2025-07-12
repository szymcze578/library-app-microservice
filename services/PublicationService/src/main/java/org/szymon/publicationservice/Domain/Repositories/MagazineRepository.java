package org.szymon.publicationservice.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.szymon.publicationservice.Domain.Model.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {
}
