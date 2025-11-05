package com.plantcare.plantcare_api.repositories;

import com.plantcare.plantcare_api.entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    boolean existsById(Long id);

}
