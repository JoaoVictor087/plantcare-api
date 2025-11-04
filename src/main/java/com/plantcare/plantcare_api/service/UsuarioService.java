//package com.plantcare.plantcare_api.service;
//
//import com.plantcare.plantcare_api.DTOs.request.CriarUsuarioDTO;
//import com.plantcare.plantcare_api.entities.Usuario;
//import com.plantcare.plantcare_api.repositories.UsuarioRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class UsuarioService {
//    private final UsuarioRepository usuarioRepository;
//
//    private void validarUsuario(CriarUsuarioDTO dto){
//        if(dto.email() == null || !dto.email().contains("@")){
//            throw new IllegalArgumentException("Email Inválido");
//        }
//        if(dto.nome() == null || dto.nome().isEmpty()){
//            throw new IllegalArgumentException("O nome não pode estar em branco");
//        }
//
//        boolean temCaractereEspecial = CARACTERES_ESPECIAIS
//                .chars().anyMatch(c -> dto.senha().contains(String.valueOf((char) c)));
//
//        if(!temCaractereEspecial){
//            throw new IllegalArgumentException("Senha deve ter pelo menos um caracter especial");
//        }
//        if(dto.senha().length() < 8){
//            throw new IllegalArgumentException("Senha deve ter pelo menos 8 caracteres");
//        }
//
//        if (usuarioRepository.existsByEmailUsuario(dto.email())){
//            throw new IllegalArgumentException("Email " + dto.email() + "já está cadastrado");
//        }
//    };
//
//    //Lógica Inicial de criação de um novo usuário, possivelmente será melhorado depois
//    @Transactional
//    public Usuario criarUsuario(CriarUsuarioDTO dto){
//        validarUsuario(dto);
//        Usuario newUsuario = new Usuario();
//        newUsuario.setNomeUsuario(dto.nome());
//        newUsuario.setEmailUsuario(dto.email());
//        newUsuario.setSenhaUsuario(dto.senha());
//
//        log.info("Usuario Criado"
//        + "\n nome: " + newUsuario.getNomeUsuario()
//        + "\n email: " + newUsuario.getEmailUsuario());
//
//        return usuarioRepository.save(newUsuario);
//    }
//
//}
