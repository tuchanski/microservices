package dev.tuchanski.ms.user.mapper;

import dev.tuchanski.ms.user.dto.UserRequestDTO;
import dev.tuchanski.ms.user.dto.UserResponseDTO;
import dev.tuchanski.ms.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        return user;
    }

}
