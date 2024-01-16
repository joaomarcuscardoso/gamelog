package com.udesc.padroesdeprojeto.gamelog.repository;

import com.udesc.padroesdeprojeto.gamelog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Boolean existsByUsernameAndEmail(String username, String email);

    Optional<User> findByToken(String token);
}
