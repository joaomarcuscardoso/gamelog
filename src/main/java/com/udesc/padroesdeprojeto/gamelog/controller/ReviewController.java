package com.udesc.padroesdeprojeto.gamelog.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.udesc.padroesdeprojeto.gamelog.abstractFactory.DetailedFactory;
import com.udesc.padroesdeprojeto.gamelog.abstractFactory.SimpleReviewFactory;
import com.udesc.padroesdeprojeto.gamelog.command.EmailCommand;
import com.udesc.padroesdeprojeto.gamelog.dto.DetailedReviewCondifDTO;
import com.udesc.padroesdeprojeto.gamelog.dto.ReviewConfigDTO;
import com.udesc.padroesdeprojeto.gamelog.model.*;
import com.udesc.padroesdeprojeto.gamelog.repository.*;
import com.udesc.padroesdeprojeto.gamelog.service.JavaMailSenderService;
import com.udesc.padroesdeprojeto.gamelog.visitor.EmailSenderVisitor;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    private final SimpleConfigRepository simpleConfigRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final GameRepository gameRepository;
    @Autowired
    private final DlcRepository dlcRepository;
    @Autowired
    private final DetailedReviewRepository detailedReviewRepository;
    @Autowired
    private final DetailedConfigRepository detailedConfigRepository;
    @Autowired
    private JavaMailSenderService mailSenderService;

    @Transactional
    @PostMapping("/{userId}")
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

        simpleConfigRepository.save(config);


        return ResponseEntity.status(HttpStatus.OK).body(config);
    }

    @Transactional
    @PostMapping("/{userId}/detailed")
    public ResponseEntity<Object> addDetailedReview(@PathVariable Integer userId,
                                                    @RequestBody @Valid DetailedReviewCondifDTO detailedReviewCondifDTO){
            DetailedFactory detailedFactory = new DetailedFactory();

            User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(("Usuario não encontrado")));

            float floatRating = detailedReviewCondifDTO.getRating();

            DetailedReview detailedReview = detailedFactory.createReview(
                    detailedReviewCondifDTO.getTitle(),
                    floatRating,
                    detailedReviewCondifDTO.getComment(),
                    user);

            if(detailedReviewCondifDTO.getIdGame() == null){
                Dlc dlc = dlcRepository.
                        findById(detailedReviewCondifDTO.getIdDlc()).
                        orElseThrow(() ->
                                new EntityNotFoundException(("Dlc não encontrada")));
                detailedReview.setDlc(dlc);
            } else{
                Game game = gameRepository.
                        findById(detailedReviewCondifDTO.getIdGame()).
                        orElseThrow(() ->
                                new EntityNotFoundException(("Game não encontrado")));
                detailedReview.setGame(game);
            }

            detailedReview.setRecommendation(detailedReviewCondifDTO.isRecommendation());
            detailedReview.setPros(detailedReviewCondifDTO.getPros());
            detailedReview.setCons(detailedReviewCondifDTO.getCons());

            detailedReviewRepository.save(detailedReview);

            DetailedConfig detailedConfig = detailedFactory.createConfigs(
                    detailedReviewCondifDTO.getPlatform(),
                    detailedReviewCondifDTO.getCompletion()
            );

            detailedConfig.setSetup(detailedReviewCondifDTO.getSetup());
            detailedConfig.setGameConfigs(detailedReviewCondifDTO.getGameConfigs());
            detailedConfig.setDetailedReview(detailedReview);

             detailedConfigRepository.save(detailedConfig);
            return ResponseEntity.status(HttpStatus.OK).body(detailedConfig);
    }

    @GetMapping("/revMail/{userId}")
    public void enviarEmail(@PathVariable Integer userId){


        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(("Usuario não encontrado")));
        List<Review> reviewList = reviewRepository.findByUser(user);
        List<DetailedReview> detailedReviewsList = detailedReviewRepository.findByUser(user);

        EmailSenderVisitor emailSenderVisitor = new EmailSenderVisitor(mailSenderService, user);

        for(DetailedReview detailedReview : detailedReviewsList){
            detailedReview.accept(emailSenderVisitor);
        }

        for(Review review : reviewList){
            review.accept(emailSenderVisitor);
        }
    }
}