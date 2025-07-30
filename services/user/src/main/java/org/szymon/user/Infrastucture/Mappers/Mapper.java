package org.szymon.user.Infrastucture.Mappers;

import org.springframework.stereotype.Service;
import org.szymon.user.Domain.Model.RoleType;
import org.szymon.user.Domain.Model.User;
import org.szymon.user.WebApi.DataTransferObjects.AddressViewModel;
import org.szymon.user.WebApi.DataTransferObjects.UserViewModel;

@Service
public class Mapper {

    public UserViewModel Map(User user)
    {
        return new UserViewModel(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.isActive(),
                user.getBirthDate(),
                user.getRole().getType(),
                new AddressViewModel(
                        user.getAddress().getId(),
                        user.getAddress().getCity(),
                        user.getAddress().getStreet(),
                        user.getAddress().getHome()
                )
        );
    }
}
