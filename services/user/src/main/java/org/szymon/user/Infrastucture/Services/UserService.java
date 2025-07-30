package org.szymon.user.Infrastucture.Services;

import org.springframework.stereotype.Service;
import org.szymon.user.Domain.Repositories.UserRepository;
import org.szymon.user.Infrastucture.Interfaces.IUserService;
import org.szymon.user.Infrastucture.Mappers.Mapper;
import org.szymon.user.WebApi.DataTransferObjects.UserViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final Mapper mapper;

    public UserService(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<UserViewModel> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper::Map)
                .collect(Collectors.toList());
    }
}
