package com.ismoke.infrastructure.persistence;

import com.ismoke.domain.model.User;
import com.ismoke.domain.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<User, String> {

    @Override
    public Optional<User> findByKey(String id) {
        return find("id", id).firstResultOptional();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    @Override
    public void save(User user) {
        persist(user);
    }

    @Override
    public void update(User user) {
        // Persist também funciona para atualizar
        persist(user);
    }

    @Override
    public void delete(String id) {
        delete("id", id);
    }

    @Override
    public List<User> getAllUsers() {
        return listAll();
    }
}
