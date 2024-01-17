package com.udesc.padroesdeprojeto.gamelog.model;

import com.udesc.padroesdeprojeto.gamelog.observer.IObserver;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
        }
)
public class User implements IObserver {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=8)
    private String password;

    @NotBlank
    @Size(max=120)
    private String username;

    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private String profileImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Game> games;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Dlc> dlcs;

    @Column(nullable = true)
    private String token;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public void update(Game game) {
        System.out.println("User: " + this.username + " notified the game: " + game.getName());
    }
}
