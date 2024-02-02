package com.udesc.padroesdeprojeto.gamelog.repository;

import com.udesc.padroesdeprojeto.gamelog.model.DetailedReview;
import com.udesc.padroesdeprojeto.gamelog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailedReviewRepository extends JpaRepository<DetailedReview, Integer> {
    List<DetailedReview> findByUser(User user);
}
