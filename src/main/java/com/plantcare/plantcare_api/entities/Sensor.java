package com.plantcare.plantcare_api.entities;

import com.plantcare.plantcare_api.enums.Sensores;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "T_PC_SENSOR")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_SENSOR")
    private Long id;

    @Column(name = "TP_SENSOR", nullable = false)
    private Sensores sensor;

    @ManyToOne
    @JoinColumn(name = "id_planta")
    private Planta planta;

    @OneToMany(mappedBy = "sensor")
    private List<Leitura> leituras;

}
