package com.udesc.padroesdeprojeto.gamelog.dto;

import com.udesc.padroesdeprojeto.gamelog.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class GameRequestDTO {
    private Integer idUser;
    @NotNull(message = "Nome é obrigatório")
    private String name;

    private String released;

    private String developer;

    @NotEmpty(message = "Descrição é obrigatório")
    private String description;

    private String CoverImage;

    private boolean platinum;

    private boolean favorite;
}
