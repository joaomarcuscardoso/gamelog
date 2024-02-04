package com.udesc.padroesdeprojeto.gamelog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.udesc.padroesdeprojeto.gamelog.command.EmailCommand;
import com.udesc.padroesdeprojeto.gamelog.command.Invoker;
import com.udesc.padroesdeprojeto.gamelog.observer.IObserver;
import com.udesc.padroesdeprojeto.gamelog.service.JavaMailSenderService;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

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
    @JsonIgnore
    @JsonIgnoreProperties
    private String password;

    @NotBlank
    @Size(max=120)
    private String username;

    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private String profileImage;

    @Column(nullable = true)
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Game> games;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DetailedReview> detailedReviews;

    @Column(nullable = true)
    private String token;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public void notifyUser(MailSender mailSender, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(this.getEmail());
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
