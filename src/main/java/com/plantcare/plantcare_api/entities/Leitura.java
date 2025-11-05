package com.plantcare.plantcare_api.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "T_PC_LEITURA")
public class Leitura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_LEITURA")
    private Long id;

    @Column(name = "dt_leitura", nullable = false)
    private LocalDateTime dataLeitura;

    @Column(name = "valor", nullable = false)
    private double valor;

    @ManyToOne
    @JoinColumn(name = "id_sensor")
    private Sensor sensor;
}
