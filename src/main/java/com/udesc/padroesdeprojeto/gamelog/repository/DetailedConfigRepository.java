package com.udesc.padroesdeprojeto.gamelog.repository;

import com.udesc.padroesdeprojeto.gamelog.model.DetailedConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailedConfigRepository extends JpaRepository<DetailedConfig, Integer> {
}
