package com.udesc.padroesdeprojeto.gamelog.dto;

import com.udesc.padroesdeprojeto.gamelog.model.Review;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewConfigDTO {

    private Integer idGame;
    
    private Integer idDlc;

    @NotNull(message = "Title é obrigatório!")
    private String title;

    @DecimalMin(value = "0.0", message = "Rating deve ser maior ou igual a 0")
    @DecimalMax(value = "5.0", message = "Rating deve ser menor ou igual a 5")
    private float rating;

    @NotNull(message = "Comment é obrigatório!")
    private String comment;

    @NotNull(message = "Platform é obrigatório!")
    private String platform;

    @NotNull(message = "Completion é obrigatório!")
    private String completion;

    private Review review;

}
