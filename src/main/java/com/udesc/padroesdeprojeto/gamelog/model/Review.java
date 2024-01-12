package com.udesc.padroesdeprojeto.gamelog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer id;

    //Entre 0 a 5 e pode ser uma nota tipo 3.5
    private float nota;

    private String comment;


}
