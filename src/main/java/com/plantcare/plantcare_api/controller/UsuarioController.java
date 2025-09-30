package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.CriarUsuarioRequestDTO;
import com.plantcare.plantcare_api.service.UsuarioService;
import jakarta.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Inject
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody CriarUsuarioRequestDTO dto){

    }
}
