package org.szymon.user.Infrastucture.Interfaces;

import org.szymon.user.WebApi.DataTransferObjects.UserViewModel;

import java.util.List;

public interface IUserService {
    List<UserViewModel> getUsers();
}
