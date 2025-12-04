package dev.tuchanski.ms.user.service.impl;

import dev.tuchanski.ms.user.dto.UserRequestDTO;
import dev.tuchanski.ms.user.dto.UserResponseDTO;
import dev.tuchanski.ms.user.mapper.UserMapper;
import dev.tuchanski.ms.user.model.User;
import dev.tuchanski.ms.user.producer.UserProducer;
import dev.tuchanski.ms.user.repository.UserRepository;
import dev.tuchanski.ms.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserProducer userProducer;

    @Override
    @Transactional
    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.email())) {
            throw new IllegalArgumentException("E-mail already registered");
        }

        User user = userMapper.toEntity(userRequestDTO);
        user = userRepository.save(user);
        userProducer.publishMessageEmail(user);

        return userMapper.toDto(user);
    }

}
