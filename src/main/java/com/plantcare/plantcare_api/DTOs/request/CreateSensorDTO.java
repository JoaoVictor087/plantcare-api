package com.plantcare.plantcare_api.DTOs.request;

import com.plantcare.plantcare_api.enums.Sensores;

public record CreateSensorDTO(
        Sensores tipoSensor
){}
