package com.ismoke.domain.model;

public class User {
    private String name;
    private String email;
    private String phone;
    private String cpf;

    private User(UserBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.cpf = builder.cpf;
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

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String name;
        private String email;
        private String phone;
        private String cpf;

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

        public User build() {
            return new User(this);

        }
    }
}
