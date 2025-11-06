package com.plantcare.plantcare_api.service;

import com.plantcare.plantcare_api.DTOs.request.CriarUsuarioDTO;
import com.plantcare.plantcare_api.DTOs.request.LoginRequestDTO;
import com.plantcare.plantcare_api.DTOs.response.LoginResponseDTO;
import com.plantcare.plantcare_api.entities.Usuario;
import com.plantcare.plantcare_api.enums.Role;
import com.plantcare.plantcare_api.exceptions.EmailException;
import com.plantcare.plantcare_api.exceptions.SenhaException;
import com.plantcare.plantcare_api.repositories.UsuarioRepository;
import com.plantcare.plantcare_api.security.JWT.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.plantcare.plantcare_api.utils.SenhaUtils.isSenhaValida;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder bCrypt;
    private final UsuarioRepository usuarioRepository;
    private final JWTTokenProvider jwtTokenProvider;

    public Usuario criarConta(CriarUsuarioDTO dto) {
        if (usuarioRepository.existsByEmailUsuario(dto.email())) {
            log.debug("email {}já cadastrado", dto.email());
            throw new EmailException("Email já cadastrado");
        }
        if (!isSenhaValida(dto.senha())) {
            log.debug("senha não válida");
            throw new SenhaException("Senha não é válida");
        }

        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.nome());
        usuario.setEmailUsuario(dto.email());

        usuario.setRole(Role.ROLE_USER);

        String senhaCriptografada = bCrypt.encode(dto.senha());

        usuario.setSenhaUsuario(senhaCriptografada);
        usuarioRepository.save(usuario);
        log.debug("Usuário {} cadastrado com sucesso", dto.nome());
        return usuario;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        var usuarioSenha = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());

        var auth = authenticationManager.authenticate(usuarioSenha);
        var token = jwtTokenProvider.gerarToken((Usuario) auth.getPrincipal());

        LoginResponseDTO loginSucesso = new LoginResponseDTO(token);
        log.debug("usuário {} logado com sucesso", dto.email());
        return loginSucesso;
    }

}
