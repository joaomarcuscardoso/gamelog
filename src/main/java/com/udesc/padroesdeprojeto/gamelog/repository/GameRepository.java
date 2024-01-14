package com.udesc.padroesdeprojeto.gamelog.repository;

import com.udesc.padroesdeprojeto.gamelog.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
