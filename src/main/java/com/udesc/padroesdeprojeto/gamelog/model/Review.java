package com.udesc.padroesdeprojeto.gamelog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.udesc.padroesdeprojeto.gamelog.abstractFactory.Reviews;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reviews")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Review implements Reviews {

    public Review(String title, float rating, String comment, User user){
        this.title = title;
        this.rating = rating;
        this.comment = comment;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    //Entre 0 a 5 e pode ser uma nota tipo 3.5
    @Size(max = 5, min = 0)
    private float rating;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "dlc_id")
    private Dlc dlc;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private SimpleConfig simpleConfig;

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
