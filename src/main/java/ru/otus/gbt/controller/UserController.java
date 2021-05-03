package ru.otus.gbt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.gbt.dto.UserDto;
import ru.otus.gbt.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/api/user")
    public UserDto getAuthenticatedUserDto() {
        return UserDto.fromUser(userService.getAuthenticatedUser());
    }
}
