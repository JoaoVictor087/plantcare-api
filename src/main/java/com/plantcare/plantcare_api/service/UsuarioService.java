package com.plantcare.plantcare_api.service;

import com.plantcare.plantcare_api.DTOs.CriarUsuarioRequestDTO;
import com.plantcare.plantcare_api.entities.Usuario;
import com.plantcare.plantcare_api.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private static final String CARACTERES_ESPECIAIS = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
    private final UsuarioRepository usuarioRepository;


    public void validarUsuario(CriarUsuarioRequestDTO dto){
        if(dto.email() == null || !dto.email().contains("@")){
            throw new IllegalArgumentException("Email Inválido");
        }
        if(dto.nome() == null || dto.nome().isEmpty()){
            throw new IllegalArgumentException("O nome não pode estar em branco");
        }

        boolean temCaractereEspecial = CARACTERES_ESPECIAIS
                .chars().anyMatch(c -> dto.senha().contains(String.valueOf((char) c)));

        if(!temCaractereEspecial){
            throw new IllegalArgumentException("Senha deve ter pelo menos um caracter especial");
        }
        if(dto.senha().length() < 8){
            throw new IllegalArgumentException("Senha deve ter pelo menos 8 caracteres");
        }

        if (usuarioRepository.existsByEmailUsuario(dto.email())){
            throw new IllegalArgumentException("Email " + dto.email() + "já está cadastrado");
        }
    };

    //Lógica Inicial de criação de um novo usuário, possivelmente será melhorado depois
    @Transactional
    public Usuario criarUsuario(CriarUsuarioRequestDTO dto){
        validarUsuario(dto);
        Usuario newUsuario = new Usuario();
        newUsuario.setNomeUsuario(dto.nome());
        newUsuario.setEmailUsuario(dto.email());
        newUsuario.setSenhaUsuario(dto.senha());



        log.info("Usuario Criado"
        + "\n nome: " + newUsuario.getNomeUsuario()
        + "\n email: " + newUsuario.getEmailUsuario());

        return usuarioRepository.save(newUsuario);
    }

}
