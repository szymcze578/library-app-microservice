package org.szymon.user.Infrastucture.Services;

import lombok.AllArgsConstructor;
import org.apache.commons.configuration.AbstractFileConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.szymon.user.Domain.Model.Address;
import org.szymon.user.Domain.Model.User;
import org.szymon.user.Domain.Repositories.AddressRepository;
import org.szymon.user.Domain.Repositories.UserRepository;
import org.szymon.user.Infrastucture.Interfaces.IUserService;
import org.szymon.user.Infrastucture.Mappers.AddressMapper;
import org.szymon.user.Infrastucture.Mappers.Mapper;
import org.szymon.user.WebApi.DataTransferObjects.AddressRequest;
import org.szymon.user.WebApi.DataTransferObjects.UserViewModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final Mapper mapper;
    private static final Logger logger =  LoggerFactory.getLogger(UserService.class);

    public List<UserViewModel> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper::Map)
                .collect(Collectors.toList());
    }

    public Address createAddressForUser(Long userId, AddressRequest addressRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = AddressMapper.Map(addressRequest);

        addressRepository.save(address);

        user.setAddress(address);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return address;
    }
}
