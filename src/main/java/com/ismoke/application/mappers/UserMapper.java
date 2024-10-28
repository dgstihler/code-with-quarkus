package com.ismoke.application.mappers;

import com.ismoke.application.builders.UserBuilder;
import com.ismoke.application.dtos.UserDTO;
import com.ismoke.domain.entities.User;
import com.ismoke.infrastructure.entities.UserEntity;

public class UserMapper {

    private UserMapper() {
    }

    public static User toUserDomain(UserDTO userDTO) {
        return new UserBuilder()
            .name(userDTO.getName())
            .email(userDTO.getEmail())
            .phone(userDTO.getPhone())
            .cpf(userDTO.getCpf())
            .buildUser();
    }

    public static UserDTO toUserDTO(User user) {
        return new UserBuilder()
            .name(user.getName())
            .email(user.getEmail())
            .phone(user.getPhone())
            .cpf(user.getCpf())
            .buildUserDTO();
    }

    public static UserEntity toEntity(User user) {
        return new UserBuilder()
            .name(user.getName())
            .email(user.getEmail())
            .phone(user.getPhone())
            .cpf(user.getCpf())
            .buildUserEntity();
    }

    public static User toUserDomain(UserEntity userEntity) {
        return new UserBuilder()
            .name(userEntity.getName())
            .email(userEntity.getEmail())
            .phone(userEntity.getPhone())
            .cpf(userEntity.getCpf())
            .buildUser();
    }
}
