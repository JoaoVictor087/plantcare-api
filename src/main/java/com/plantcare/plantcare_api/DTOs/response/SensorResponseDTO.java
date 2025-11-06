package com.plantcare.plantcare_api.DTOs.response;

import com.plantcare.plantcare_api.enums.Sensores;

public record SensorResponseDTO(
        Long id,
        Sensores sensor,
        Long plantaId
) {}