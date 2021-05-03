package ru.otus.gbt.service;

import ru.otus.gbt.domain.User;

public interface UserService {
    User getAuthenticatedUser();
}
