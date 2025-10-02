package com.plantcare.plantcare_api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "T_PC_USUARIOS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idUsuario;

    @Column(name = "nm_usuario", length = 200, nullable = false)
    private String nomeUsuario;

    @Column(name = "email", length = 100, nullable = false)
    private String emailUsuario;

    @Column(name = "senha", length = 50, nullable = false)
    private String senhaUsuario;
}
