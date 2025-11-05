package com.plantcare.plantcare_api.service;

import com.plantcare.plantcare_api.DTOs.request.AtualizarUsuarioDTO;
import com.plantcare.plantcare_api.entities.Usuario;
import com.plantcare.plantcare_api.exceptions.EmailException;
import com.plantcare.plantcare_api.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder bcrypt;

    public Usuario atualizarDadosUsuario(AtualizarUsuarioDTO dto, Usuario usuarioLogado) {
        Usuario usuario = usuarioLogado;

        if (dto.nome() != null && !dto.nome().isBlank()) {
            usuario.setNomeUsuario(dto.nome());
        }
        String novoEmail = dto.email();

        if (novoEmail != null && !novoEmail.isBlank() && !novoEmail.equals(usuario.getEmailUsuario())) {

            Optional<Usuario> usuarioExistente = usuarioRepository.findByEmailUsuario(novoEmail);

            if (usuarioExistente.isPresent()) {

                throw new EmailException("O e-mail " + novoEmail + " já está em uso.");
            }
            usuario.setEmailUsuario(novoEmail);
        }

        if (dto.senha() != null && !dto.senha().isEmpty()) {
            usuario.setSenhaUsuario(bcrypt.encode(dto.senha()));
        }
        usuarioRepository.save(usuario);
        return usuario;
    }
}