package com.ismoke.application.builders;

import com.ismoke.application.dtos.UserDTO;
import com.ismoke.domain.entities.User;
import com.ismoke.infrastructure.entities.UserEntity;

public class UserBuilder {
    private String name;
    private String email;
    private String phone;
    private String cpf;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCpf() {
        return cpf;
    }

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public User buildUser() {
        return User.build(this);
    }

    public UserDTO buildUserDTO() {
        return UserDTO.build(this);
    }

    public UserEntity buildUserEntity() {
        return UserEntity.build(this);
    }
}
