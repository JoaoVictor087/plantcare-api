package com.plantcare.plantcare_api.DTOs.response;

public record LoginResponseDTO (
        String token,
        String refreshToken,
        Long userId
){
}
