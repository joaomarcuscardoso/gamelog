package com.udesc.padroesdeprojeto.gamelog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String nickname;

    @NotBlank
    private String password;
}