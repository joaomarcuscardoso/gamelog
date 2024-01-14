package com.udesc.padroesdeprojeto.gamelog.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    //Entre 0 a 5 e pode ser uma nota tipo 3.5
    private float rating;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
