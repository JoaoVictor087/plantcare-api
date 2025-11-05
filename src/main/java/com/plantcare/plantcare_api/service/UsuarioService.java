package com.plantcare.plantcare_api.service;

import com.plantcare.plantcare_api.DTOs.request.AtualizarUsuarioDTO;
import com.plantcare.plantcare_api.entities.Usuario;
import com.plantcare.plantcare_api.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder bcrypt;

    public Usuario atualizarDadosUsuario(AtualizarUsuarioDTO dto, Usuario usuarioLogado) {
        Usuario usuario = usuarioLogado;

        if (!dto.nome().isEmpty()){
            usuario.setNomeUsuario(dto.nome());
        }
        if(!dto.email().isEmpty()){
            usuario.setEmailUsuario(dto.email());
        }
        if(!dto.senha().isEmpty()){
            usuario.setSenhaUsuario(bcrypt.encode(dto.senha()));
        }
        usuarioRepository.save(usuario);
        return usuario;
    }
}