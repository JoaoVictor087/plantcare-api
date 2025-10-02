package com.plantcare.plantcare_api.repositories;

import com.plantcare.plantcare_api.entities.Planta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long> {
    boolean existsById(long id);
    Planta getPlantaById(Long id);
    Page<Planta>findByUsuario_IdUsuario(long id_usuario, Pageable pageable);

    @Override
    void deleteById(Long aLong);
}
