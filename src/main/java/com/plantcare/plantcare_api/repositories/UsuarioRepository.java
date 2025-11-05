package com.plantcare.plantcare_api.repositories;

import com.plantcare.plantcare_api.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmailUsuario(String email);
    Usuario findByIdUsuario(long idUsuario);

    Optional<Usuario> findByEmailUsuarioIgnoreCase(String email);

    Optional<Usuario> findByEmailUsuario(String novoEmail);
}
