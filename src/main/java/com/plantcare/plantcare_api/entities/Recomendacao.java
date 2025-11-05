package com.plantcare.plantcare_api.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "T_PC_RECOMENDACAO")
public class Recomendacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_recomendacao", nullable = false)
    private Long id;

    @Column(name = "dt_registro_recomendacao", nullable = false)
    private LocalDateTime registroRecomendacao;

    @Column(name = "msg_recomendacao", nullable = false)
    private String mensagemRecomdancao;

    @ManyToOne
    @JoinColumn(name = "id_planta")
    private Planta planta;
}

