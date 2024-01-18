package com.udesc.padroesdeprojeto.gamelog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.udesc.padroesdeprojeto.gamelog.abstractFactory.Configs;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "simple_config")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SimpleConfig implements Configs {

    public SimpleConfig(String platform, String completion){
        this.platform = platform;
        this.completion = completion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String platform;

    private String completion;

    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;
    @Override
    public Integer getReviewId() {
        if (review == null) {
            return 0;
        }

        return review.getId();
    }
}
