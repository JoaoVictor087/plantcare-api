package com.plantcare.plantcare_api.repositories;

import com.plantcare.plantcare_api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmailUsuario(String email);
    Usuario findByIdUsuario(long idUsuario);
}
