package org.szymon.user.WebApi.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.szymon.user.Domain.Model.Address;
import org.szymon.user.Infrastucture.Interfaces.IUserService;
import org.szymon.user.Infrastucture.Services.UserService;
import org.szymon.user.WebApi.DataTransferObjects.AddressRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;


    @GetMapping("")
    public ResponseEntity<?> getUsers()
    {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/{userId}/address")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> setAddress(@PathVariable Long userId, @Valid @RequestBody AddressRequest address)
    {
        Address newAddress = userService.createAddressForUser(userId, address);
        return ResponseEntity.ok().build();
    }

}
