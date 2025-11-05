package com.plantcare.plantcare_api.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "T_PC_ALERTAS")
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "dt_registro_alerta", nullable = false)
    private LocalDateTime registroAlerta;

    @Column(name = "msg_alerta", nullable = false)
    private String mensagemAlerta;

    @ManyToOne
    @JoinColumn(name = "id_planta")
    private Planta planta;
}
