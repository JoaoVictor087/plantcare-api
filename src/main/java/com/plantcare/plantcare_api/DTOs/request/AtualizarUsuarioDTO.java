package com.plantcare.plantcare_api.DTOs.request;

public record AtualizarUsuarioDTO(
        String nome,
        String email,
        String senha
){}
