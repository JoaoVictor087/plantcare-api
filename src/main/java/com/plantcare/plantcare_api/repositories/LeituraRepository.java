package com.plantcare.plantcare_api.repositories;

import com.plantcare.plantcare_api.entities.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LeituraRepository extends JpaRepository<Leitura, Long> {
}
