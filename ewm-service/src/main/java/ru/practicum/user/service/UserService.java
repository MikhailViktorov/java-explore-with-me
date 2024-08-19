package ru.practicum.user.service;

import ru.practicum.user.dto.NewUserRequest;
import ru.practicum.user.dto.UserDto;

public interface UserService {
    UserDto addUser(NewUserRequest newUserRequest);
    void deleteUser(Long userId);
}
