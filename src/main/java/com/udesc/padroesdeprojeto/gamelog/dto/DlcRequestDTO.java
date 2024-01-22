package com.udesc.padroesdeprojeto.gamelog.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DlcRequestDTO {

    @NotNull(message = "Nome é obrigatório")
    private String name;

    private String released;

    private String developer;

    private String description;

    private String CoverImage;
}
