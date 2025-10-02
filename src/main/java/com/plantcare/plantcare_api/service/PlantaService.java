package com.plantcare.plantcare_api.service;

import com.plantcare.plantcare_api.DTOs.PlantaDTO;
import com.plantcare.plantcare_api.entities.Planta;
import com.plantcare.plantcare_api.repositories.PlantaRepository;
import com.plantcare.plantcare_api.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class PlantaService {
    private final PlantaRepository plantaRepository;
    private final UsuarioRepository usuarioRepository;
    private static final Logger log = LoggerFactory.getLogger(PlantaService.class);

    public Planta adicionarPlanta(PlantaDTO dto, long id_usuario){
        LocalDateTime dateTime = LocalDateTime.now();

        Planta newPlanta = new Planta();
        newPlanta.setNomePlanta(dto.nomePlanta());
        newPlanta.setTipoPlanta(dto.tipoPlanta());
        newPlanta.setDataCadastro(dateTime);
        newPlanta.setUsuario(usuarioRepository.findByIdUsuario(id_usuario));

        log.info("Planta criada:" +
                "\nNome da planta: " + newPlanta.getNomePlanta() +
                "\nTipo da planta: " + newPlanta.getTipoPlanta() +
                "\nData e hora: " + newPlanta.getDataCadastro() +
                "\nDono da planta: " + newPlanta.getUsuario());
        return plantaRepository.save(newPlanta);
    }

    public Planta atualizarPlanta(PlantaDTO dto, long id_planta) throws EmptyResultDataAccessException{
        if (plantaRepository.existsById(id_planta)){
            Planta planta = plantaRepository.getPlantaById(id_planta);
            planta.setTipoPlanta(dto.tipoPlanta());
            planta.setNomePlanta(dto.nomePlanta());
            log.info("Planta atualizada com sucesso");
            return plantaRepository.save(planta);
        }else{
            throw new EmptyResultDataAccessException("Planta não encontrada", 1);
        }
    }

    public Page<Planta> listarPlantasDoUsuario(Pageable pageable, long id_usuario){
        return plantaRepository.findByUsuario_IdUsuario(id_usuario, pageable);
    }

    public void deletarPlantaPorId(long id_planta){
        Planta planta = plantaRepository.getPlantaById(id_planta);
        if (planta == null){
            throw new NullPointerException("Planta não encontrada no banco de dados");
        }
        plantaRepository.deleteById(id_planta);
        log.info("Planta excluída com sucesso");
    }
}
