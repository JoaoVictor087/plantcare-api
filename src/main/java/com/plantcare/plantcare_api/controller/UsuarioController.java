package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.request.AtualizarUsuarioDTO;
import com.plantcare.plantcare_api.entities.Usuario;
import com.plantcare.plantcare_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/usuario")
@Slf4j
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PutMapping("/me")
    public ResponseEntity<?> atualizarNomeUsuario(@RequestBody AtualizarUsuarioDTO dto,
                                                  @AuthenticationPrincipal Usuario usuarioLogado){
        usuarioService.atualizarDadosUsuario(dto, usuarioLogado);
        return ResponseEntity.ok().build();
    }
}