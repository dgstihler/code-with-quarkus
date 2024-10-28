package com.ismoke.application.dtos;

import com.ismoke.application.builders.UserBuilder;

public class UserDTO {
    private final String name;
    private final String email;
    private final String phone;
    private final String cpf;

    private UserDTO(UserBuilder userBuilder) {
        this.name = userBuilder.getName();
        this.email = userBuilder.getEmail();
        this.phone = userBuilder.getPhone();
        this.cpf = userBuilder.getCpf();
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

    public static UserDTO build(UserBuilder userBuilder) {
        return new UserDTO(userBuilder);
    }

}

