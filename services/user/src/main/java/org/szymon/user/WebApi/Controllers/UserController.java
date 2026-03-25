package org.szymon.user.WebApi.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.szymon.user.Infrastucture.Interfaces.IUserService;
import org.szymon.user.Infrastucture.Services.UserService;

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

}
