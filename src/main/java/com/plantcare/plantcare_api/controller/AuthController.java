package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.request.CriarUsuarioDTO;
import com.plantcare.plantcare_api.DTOs.request.LoginRequestDTO;
import com.plantcare.plantcare_api.DTOs.response.LoginResponseDTO;
import com.plantcare.plantcare_api.DTOs.response.RefreshTokenDTO;
import com.plantcare.plantcare_api.entities.Usuario;
import com.plantcare.plantcare_api.exceptions.EmailException;
import com.plantcare.plantcare_api.repositories.UsuarioRepository;
import com.plantcare.plantcare_api.security.JWT.JWTTokenProvider;
import com.plantcare.plantcare_api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/criarConta")
    public ResponseEntity<?>criarConta (@RequestBody @Valid CriarUsuarioDTO dto){
        authService.criarConta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?>login (@RequestBody @Valid LoginRequestDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDTO dto) {
        LoginResponseDTO response = authService.refreshToken(dto);
        return ResponseEntity.ok(response);
    }

}
