package com.ismoke;

import com.ismoke.domain.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private final String name = "Diogo Galdino";
    private final String email = "dgstihler@gmail.com";
    private final String cpf = "05555982925";
    private final String phone = "47996178220";

    @Test
    public void testUserGetters() {

        User user = User.builder()
            .name(name)
            .email(email)
            .cpf(cpf)
            .phone(phone)
            .build();

        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(cpf, user.getCpf());
        assertEquals(phone, user.getPhone());
    }

}
