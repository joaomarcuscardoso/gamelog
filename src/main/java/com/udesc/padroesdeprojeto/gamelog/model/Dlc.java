package com.udesc.padroesdeprojeto.gamelog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.udesc.padroesdeprojeto.gamelog.factory.Games;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dlcs")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Dlc implements Games {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(nullable = false)
    @Nullable
    private String released;

    @Column(nullable = false)
    @Nullable
    private String developer;

    private String description;

    @Column(nullable = false)
    @Nullable
    private String CoverImage;

    private String extraContentAdded;

    @OneToMany(mappedBy = "dlc", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "dlc", cascade = CascadeType.ALL)
    private List<DetailedReview> detailedReviews;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

}
