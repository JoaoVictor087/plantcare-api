package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.CriarUsuarioRequestDTO;
import com.plantcare.plantcare_api.repositories.UsuarioRepository;
import com.plantcare.plantcare_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UsuarioService usuarioService;

    public ResponseEntity<?>criarConta (CriarUsuarioRequestDTO dto){
        try {
            usuarioService.criarUsuario(dto);
        }catch ()
    }
}
