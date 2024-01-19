package com.udesc.padroesdeprojeto.gamelog.controller;

import com.udesc.padroesdeprojeto.gamelog.abstractFactory.SimpleReviewFactory;
import com.udesc.padroesdeprojeto.gamelog.dto.ReviewConfigDTO;
import com.udesc.padroesdeprojeto.gamelog.model.*;
import com.udesc.padroesdeprojeto.gamelog.repository.DlcRepository;
import com.udesc.padroesdeprojeto.gamelog.repository.GameRepository;
import com.udesc.padroesdeprojeto.gamelog.repository.ReviewRepository;
import com.udesc.padroesdeprojeto.gamelog.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final GameRepository gameRepository;
    @Autowired
    private final DlcRepository dlcRepository;

    @Transactional
    @PostMapping("/review/{userId}")
    public ResponseEntity<Object> addReview(@PathVariable Integer userId,
                                            @RequestBody @Valid ReviewConfigDTO reviewConfigDTO){
        SimpleReviewFactory simpleReviewFactory = new SimpleReviewFactory();

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(("Usuario não encontrado")));

        Review review = simpleReviewFactory.createReview(
                reviewConfigDTO.getTitle(),
                reviewConfigDTO.getRating(),
                reviewConfigDTO.getComment(),
                user);


        if(reviewConfigDTO.getIdGame() == null){
            Dlc dlc = dlcRepository.
                    findById(reviewConfigDTO.getIdDlc()).
                    orElseThrow(() ->
                            new EntityNotFoundException(("Dlc não encontrada")));
            review.setDlc(dlc);
        } else{
            Game game = gameRepository.
                    findById(reviewConfigDTO.getIdGame()).
                    orElseThrow(() ->
                            new EntityNotFoundException(("Game não encontrado")));
            review.setGame(game);
        }

        reviewRepository.save(review);

        SimpleConfig config = simpleReviewFactory.createConfigs(
                reviewConfigDTO.getPlatform(),
                reviewConfigDTO.getCompletion());

        config.setReview(review);


        return ResponseEntity.status(HttpStatus.OK).body(config);
    }

    @PostMapping("/detailed")
    public ResponseEntity<Object> addDetailedReview(){
        return null;
    }
}
