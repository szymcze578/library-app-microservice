package org.szymon.user.Domain.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.szymon.user.Domain.Model.User;
import org.szymon.user.WebApi.DataTransferObjects.UserViewModel;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
