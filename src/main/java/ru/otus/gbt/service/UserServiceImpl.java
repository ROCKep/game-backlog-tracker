package ru.otus.gbt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gbt.domain.User;
import ru.otus.gbt.domain.UserRole;
import ru.otus.gbt.dto.UserDto;
import ru.otus.gbt.exception.NotFoundException;
import ru.otus.gbt.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() ->
                new NotFoundException("Пользователь с именем %s не найден"));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> getAuthenticatedUserDto() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .map(user -> new UserDto(
                        user.getUsername(),
                        user.getUserRoles().stream()
                                .map(UserRole::getCode)
                                .anyMatch(code -> code.equals("ROLE_ADMIN"))));
    }
}
