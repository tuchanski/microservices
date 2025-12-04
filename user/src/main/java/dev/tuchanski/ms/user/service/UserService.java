package dev.tuchanski.ms.user.service;

import dev.tuchanski.ms.user.dto.UserRequestDTO;
import dev.tuchanski.ms.user.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO create(UserRequestDTO userRequestDTO);
}
