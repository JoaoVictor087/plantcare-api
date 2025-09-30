package com.plantcare.plantcare_api.DTOs;

public record CriarUsuarioRequestDTO(
        String nome,
        String email,
        String senha) {
}
