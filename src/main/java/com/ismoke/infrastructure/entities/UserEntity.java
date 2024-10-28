package com.ismoke.infrastructure.entities;

import com.ismoke.application.builders.UserBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cpf;

    private String name;
    private String email;
    private String phone;

    // Construtor sem argumentos necessário para o Hibernate
    protected UserEntity() {
    }

    public UserEntity(UserBuilder userBuilder) {
        this.cpf = userBuilder.getCpf();
        this.name = userBuilder.getName();
        this.email = userBuilder.getEmail();
        this.phone = userBuilder.getPhone();
    }

    // Getters
    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public static UserEntity build(UserBuilder userBuilder) {
        return new UserEntity(userBuilder);
    }
}
