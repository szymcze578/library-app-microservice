package org.szymon.user.Infrastucture.Interfaces;

import org.szymon.user.Domain.Model.Address;
import org.szymon.user.WebApi.DataTransferObjects.AddressRequest;
import org.szymon.user.WebApi.DataTransferObjects.UserViewModel;

import java.util.List;

public interface IUserService {
    List<UserViewModel> getUsers();
    Address createAddressForUser(Long userId, AddressRequest address);
}
