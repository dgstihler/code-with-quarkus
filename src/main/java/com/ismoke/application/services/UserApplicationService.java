package com.ismoke.application.services;

import com.ismoke.application.dtos.UserDTO;
import com.ismoke.application.mappers.UserMapper;
import com.ismoke.domain.entities.User;
import com.ismoke.domain.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserApplicationService {

    @Inject
    private final UserRepository userRepository;

    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = UserMapper.toUserDomain(userDTO);
        userRepository.save(user);
        return UserMapper.toUserDTO(user);
    }

    public void deleteUser(String id) {
        userRepository.delete(id);
    }

    public void update(UserDTO user) {
        userRepository.update(UserMapper.toUserDomain(user));
    }

    public Optional<UserDTO> findByKey(String id) {
        return userRepository.findByKey(id).map(UserMapper::toUserDTO);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.getAllUsers().stream()
            .map(UserMapper::toUserDTO)
            .toList();
    }
}
