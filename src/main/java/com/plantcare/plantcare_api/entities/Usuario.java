package com.plantcare.plantcare_api.entities;

import com.plantcare.plantcare_api.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "T_PC_USUARIOS")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_USUARIO")
    private long idUsuario;

    @Column(name = "nm_usuario", length = 200, nullable = false)
    private String nomeUsuario;

    @Column(name = "email", length = 255, nullable = false)
    private String emailUsuario;

    @Column(name = "senha", length = 255, nullable = false)
    private String senhaUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name =  "role", length = 20, nullable = false)
    private Role role;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getPassword() {
        return this.senhaUsuario;
    }

    @Override
    public String getUsername() {
        return this.emailUsuario;
    }
}
