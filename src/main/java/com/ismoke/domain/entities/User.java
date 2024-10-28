package com.ismoke.domain.entities;

import com.ismoke.application.builders.UserBuilder;

public class User {
    private final String name;
    private final String email;
    private final String phone;
    private final String cpf;

    private User(UserBuilder builder) {
        this.name = builder.getName();
        this.email = builder.getEmail();
        this.phone = builder.getPhone();
        this.cpf = builder.getCpf();
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

    public String getCpf() {
        return cpf;
    }

    public static User build(UserBuilder userBuilder) {
        return new User(userBuilder);
    }

}
