package com.plantcare.plantcare_api.DTOs.response;

public record LoginResponseDTO (
        String accessToken,
        String refreshToken,
        Long userId
){
}
