package com.ismoke.domain.repositories;

import com.ismoke.domain.entities.User;
import java.util.Optional;
import java.util.List;

public interface UserRepository {
    Optional<User> findByKey(String id);

    Optional<User> findByEmail(String email);

    void save(User user);

    void update(User user);

    void delete(String id);

    List<User> getAllUsers();
}