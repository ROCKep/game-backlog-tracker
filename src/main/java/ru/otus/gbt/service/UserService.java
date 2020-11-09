package ru.otus.gbt.service;

import ru.otus.gbt.domain.User;
import ru.otus.gbt.dto.UserDto;

import java.util.Optional;

public interface UserService {
    User getAuthenticatedUser();

    Optional<UserDto> getAuthenticatedUserDto();
}
