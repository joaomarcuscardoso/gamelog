package com.udesc.padroesdeprojeto.gamelog.model;

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
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
