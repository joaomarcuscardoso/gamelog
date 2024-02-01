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
@Table(name = "detailed_reviews")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class DetailedConfig implements Configs {

    public DetailedConfig(String platform, String completion){
        this.platform = platform;
        this.completion = completion;
    }

    @Id
    @GeneratedValue()
    private Integer id;

    private String platform;

    private String completion;

    private String setup;

    private String gameConfigs;

    @OneToOne
    @JoinColumn(name = "detailed_review_id")
    private DetailedReview detailedReview;

    @Override
    public Integer getReviewId() {
        if (detailedReview == null) {
            return 0;
        }

        return detailedReview.getId();
    }
}
