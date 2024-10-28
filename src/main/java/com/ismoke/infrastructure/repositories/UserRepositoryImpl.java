package com.ismoke.infrastructure.repositories;

import com.ismoke.application.mappers.UserMapper;
import com.ismoke.domain.entities.User;
import com.ismoke.domain.repositories.UserRepository;
import com.ismoke.infrastructure.entities.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity, String> {

    @Override
    public Optional<User> findByKey(String id) {
        return find("id", id)
            .firstResultOptional()
            .map(UserMapper::toUserDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return find("email", email)
            .firstResultOptional()
            .map(UserMapper::toUserDomain);
    }

    @Override
    public void save(User user) {
        persist(UserMapper.toEntity(user));
    }

    @Override
    public void update(User user) {
        persist(UserMapper.toEntity(user));
    }

    @Override
    public void delete(String id) {
        delete("id", id);
    }

    @Override
    public List<User> getAllUsers() {
        return listAll().stream()
            .map(UserMapper::toUserDomain)
            .collect(Collectors.toList());
    }
}
