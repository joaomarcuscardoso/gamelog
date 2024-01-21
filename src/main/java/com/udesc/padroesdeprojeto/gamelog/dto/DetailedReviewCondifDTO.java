package com.udesc.padroesdeprojeto.gamelog.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DetailedReviewCondifDTO {

    private Integer idGame;

    private Integer idDlc;

    @NotNull(message = "Title é obrigatório!")
    private String title;

    @Min(value = 0, message = "Rating deve ser maior ou igual a 0")
    @Max(value = 5, message = "Rating deve ser menor ou igual a 5")
    private float rating;

    @NotNull(message = "Comment é obrigatório!")
    private String comment;

    @NotNull(message = "Platform é obrigatório!")
    private String platform;

    @NotNull(message = "Completion é obrigatório!")
    private String completion;

    @NotNull(message = "Setup é obrigatório!")
    private String setup;

    @NotNull(message = "GameConfigs é obrigatório!")
    private String gameConfigs;
}
