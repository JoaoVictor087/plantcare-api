package com.plantcare.plantcare_api.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
        @NotNull(message = "O email é obrigatório.")
        @NotBlank(message = "O email é obrigatório.")
        String email,

        @NotNull(message = "A senha é obrigatória.")
        @NotBlank(message = "A senha é obrigatória.")
        String senha
){}
