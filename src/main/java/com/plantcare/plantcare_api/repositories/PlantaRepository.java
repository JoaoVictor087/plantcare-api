package com.plantcare.plantcare_api.repositories;

import com.plantcare.plantcare_api.entities.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long> {
}
