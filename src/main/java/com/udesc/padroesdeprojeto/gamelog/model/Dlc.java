package com.udesc.padroesdeprojeto.gamelog.model;

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

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getUserId() {
        if (user == null) {
            return 0;
        }

        return user.getId();
    }
}
