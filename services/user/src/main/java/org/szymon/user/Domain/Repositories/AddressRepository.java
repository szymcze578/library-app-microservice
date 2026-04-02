package org.szymon.user.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.szymon.user.Domain.Model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
