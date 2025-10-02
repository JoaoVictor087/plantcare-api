package com.plantcare.plantcare_api.service;

import com.plantcare.plantcare_api.DTOs.PlantaDTO;
import com.plantcare.plantcare_api.controller.UsuarioController;
import com.plantcare.plantcare_api.entities.Planta;
import com.plantcare.plantcare_api.entities.Usuario;
import com.plantcare.plantcare_api.repositories.PlantaRepository;
import com.plantcare.plantcare_api.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PlantaService {
    private final PlantaRepository plantaRepository;
    private final UsuarioRepository usuarioRepository;
    private static final Logger log = LoggerFactory.getLogger(PlantaService.class);

    public Planta adicionarPlanta(PlantaDTO dto, long id){
        LocalDateTime dateTime = LocalDateTime.now();

        Planta newPlanta = new Planta();
        newPlanta.setNomePlanta(dto.nomePlanta());
        newPlanta.setTipoPlanta(dto.tipoPlanta());
        newPlanta.setDataCadastro(dateTime);
        newPlanta.setUsuario(usuarioRepository.findByIdUsuario(id));

        log.info("Planta criada:" +
                "\nNome da planta: " + newPlanta.getNomePlanta() +
                "\nTipo da planta: " + newPlanta.getTipoPlanta() +
                "\nData e hora: " + newPlanta.getDataCadastro() +
                "\nDono da planta: " + newPlanta.getUsuario());
        return plantaRepository.save(newPlanta);
    }

}
