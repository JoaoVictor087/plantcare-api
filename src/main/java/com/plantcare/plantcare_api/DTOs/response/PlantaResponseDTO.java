package com.plantcare.plantcare_api.DTOs.response;

import java.time.LocalDateTime;

public record PlantaResponseDTO(
        Long id,
        String nome,
        String especie,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao,
        String imgLink,
        Double umidade,
        Double temperatura,
        String status
) {}
