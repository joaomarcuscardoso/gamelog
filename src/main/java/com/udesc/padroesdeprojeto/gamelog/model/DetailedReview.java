package com.udesc.padroesdeprojeto.gamelog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.udesc.padroesdeprojeto.gamelog.abstractFactory.Reviews;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "detailed_reviews")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class DetailedReview implements Reviews {

    @Id
    @GeneratedValue()
    private Integer id;

    public DetailedReview(String title, float rating, String comment, User user){
        this.title = title;
        this.rating = rating;
        this.comment = comment;
        this.user = user;
    }



    private String title;

    @Min(value = 0, message = "Rating deve ser maior ou igual a 0")
    @Max(value = 5, message = "Rating deve ser menor ou igual a 5")
    private float rating;

    private String comment;

    private boolean recommendation;

    @ElementCollection
    @CollectionTable(name = "detailed_review_pros", joinColumns = @JoinColumn(name = "detailed_review_id"))
    @Column(name = "pro")
    private List<String> pros;

    @ElementCollection
    @CollectionTable(name = "detailed_review_cons", joinColumns = @JoinColumn(name = "detailed_review_id"))
    @Column(name = "con")
    private List<String> cons;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "dlc_id")
    private Dlc dlc;

    @OneToOne(mappedBy = "detailedReview", cascade = CascadeType.ALL, orphanRemoval = true)
    private DetailedConfig detailedConfig;

    @Override
    public Integer getGameId() {
        if (game == null) {
            return 0;
        }

        return game.getId();
    }

    @Override
    public Integer getUserId() {
        if (user == null) {
            return 0;
        }

        return user.getId();
    }

    @Override
    public Integer getDlcId() {
        if (dlc == null) {
            return 0;
        }

        return dlc.getId();
    }
}
