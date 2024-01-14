package com.udesc.padroesdeprojeto.gamelog.repository;

import com.udesc.padroesdeprojeto.gamelog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);

    Boolean existsByNickname(String nickname);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
