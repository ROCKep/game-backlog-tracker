package ru.otus.gbt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.otus.gbt.domain.User;
import ru.otus.gbt.domain.UserRole;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private String username;
    private boolean isAdmin;

    public static UserDto fromUser(@NonNull User user) {
        return new UserDto(user.getUsername(),
                user.getUserRoles().stream()
                        .map(UserRole::getCode)
                        .anyMatch(code -> code.equals("ROLE_ADMIN")));
    }
}
