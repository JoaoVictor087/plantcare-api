package com.plantcare.plantcare_api.DTOs.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarUsuarioDTO(
        @NotNull(message = "O nome é obrigatório.")
        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotNull(message = "O e-mail é obrigatório.")
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email
        String email,

        @NotNull(message = "A senha é obrigatória.")
        @NotBlank(message = "A senha é obrigatória.")
        String senha) {
}
