package com.udesc.padroesdeprojeto.gamelog.model;

import com.fasterxml.jackson.annotation.*;
import com.udesc.padroesdeprojeto.gamelog.factory.Games;
import com.udesc.padroesdeprojeto.gamelog.state.DrawState;
import com.udesc.padroesdeprojeto.gamelog.state.IGameState;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "games")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Game implements Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<DetailedReview> detailedReviews;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Dlc> dlcs;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private IGameState state;

    public Integer getUserId() {
        if (user == null) {
            return 0;
        }

        return user.getId();
    }

    public Game() {
        this.state = new DrawState(this);
    }
}