package com.plantcare.plantcare_api.service;

import com.plantcare.plantcare_api.DTOs.CriarUsuarioRequestDTO;
import com.plantcare.plantcare_api.entities.Usuario;
import com.plantcare.plantcare_api.repositories.UsuarioRepository;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService {
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Inject
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(@RequestBody CriarUsuarioRequestDTO dto){
        Usuario newUsuario = new Usuario();
        newUsuario.setNomeUsuario(dto.nome());
        newUsuario.setEmailUsuario(dto.email());
        newUsuario.setSenhaUsuario(dto.senha());

        log.info("Usuario Criado"
        + "\n nome: " + newUsuario.getNomeUsuario()
        + "\n email: " + newUsuario.getEmailUsuario());
        return newUsuario;
    }
}
