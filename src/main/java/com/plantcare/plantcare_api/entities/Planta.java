package com.plantcare.plantcare_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "T_PC_PLANTAS")
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_PLANTA")
    private Long id;

    @Column(name = "NM_PLANTA", nullable = false)
    private String nomePlanta;

    @Column(name = "TIPO_PLANTA", nullable = false)
    private String tipoPlanta;

    @Column(name = "DT_CADASTRO", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "DT_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    @Column(name = "imagem")
    private String imgLink;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "planta")
    private List<Sensor> sensores;

    @OneToMany(mappedBy = "planta")
    private List<Alerta> alertas;

    @OneToMany(mappedBy = "planta")
    private List<Recomendacao> recomendacoes;
}
