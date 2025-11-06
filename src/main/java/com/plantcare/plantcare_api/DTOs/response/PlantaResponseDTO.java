package com.plantcare.plantcare_api.DTOs.response;

import java.time.LocalDateTime;

public record PlantaResponseDTO(
        Long id,
        String nomePlanta,
        String tipoPlanta,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao,
        String imgLink
) {}
