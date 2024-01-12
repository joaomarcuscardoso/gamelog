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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;

    private String password;

    private String nickname;

    @Column(nullable = false)
    @Nullable
    private String bio;

    @Column(nullable = false)
    @Nullable
    private String profileImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Game> games;

}
