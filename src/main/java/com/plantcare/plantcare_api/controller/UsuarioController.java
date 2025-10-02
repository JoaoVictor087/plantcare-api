package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.CriarUsuarioRequestDTO;
import com.plantcare.plantcare_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;

    @PostMapping("/criarUsuario")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody CriarUsuarioRequestDTO dto){
        try {
            usuarioService.criarUsuario(dto);
            log.info("Usuário " + dto.nome() + " criado com sucesso");
            return new ResponseEntity<>("Usuário criado com sucesso", HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
