package com.plantcare.plantcare_api.repositories;

import com.plantcare.plantcare_api.entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    boolean existsById(Long id);
    List<Sensor> findByPlantaId(Long idPlanta);

    Sensor findSensorById(Long id);
}
