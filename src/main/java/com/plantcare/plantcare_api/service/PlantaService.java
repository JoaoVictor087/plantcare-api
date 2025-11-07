package com.plantcare.plantcare_api.service;

import com.plantcare.plantcare_api.DTOs.request.PlantaDTO;
import com.plantcare.plantcare_api.entities.Planta;
import com.plantcare.plantcare_api.entities.Usuario;
import com.plantcare.plantcare_api.enums.Role;
import com.plantcare.plantcare_api.repositories.PlantaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class PlantaService {
    private final PlantaRepository plantaRepository;
    public Planta adicionarPlanta(PlantaDTO dto, Usuario usuarioLogado){
        Planta newPlanta = new Planta();
        newPlanta.setNome(dto.nome());
        newPlanta.setEspecie(dto.especie());
        newPlanta.setDataCadastro(LocalDateTime.now());
        newPlanta.setUsuario(usuarioLogado);

        newPlanta.setUmidade(0.0);
        newPlanta.setTemperatura(0.0);
        newPlanta.setStatus("Monitorando...");

        log.info("Planta criada:" +
                "\nNome da planta: " + newPlanta.getNome() +
                "\nTipo da planta: " + newPlanta.getEspecie() +
                "\nData e hora: " + newPlanta.getDataCadastro() +
                "\nDono da planta: " + newPlanta.getUsuario());
        return plantaRepository.save(newPlanta);
    }

    public List<Planta> listarPlantas(Usuario usuarioLogado) {
        if (usuarioLogado.getRole().equals(Role.ROLE_ADMIN)) {
            return plantaRepository.findAll();
        }
        return plantaRepository.findByUsuario(usuarioLogado);
    }

    public Planta buscarPlantaPorId(Long id, Usuario usuarioLogado) {
        Planta planta = getPlantaById(id);
        checkOwnership(planta, usuarioLogado);
        return planta;
    }

    public Planta atualizarPlanta(Long id, PlantaDTO plantaDTO, Usuario usuarioLogado) {
        Planta planta = getPlantaById(id);
        checkOwnership(planta, usuarioLogado);

        planta.setNome(plantaDTO.nome());
        planta.setEspecie(plantaDTO.especie());
        planta.setDataAtualizacao(LocalDateTime.now());
        return plantaRepository.save(planta);
    }

    public void deletarPlanta(Long id, Usuario usuarioLogado) {
        Planta planta = getPlantaById(id);
        checkOwnership(planta, usuarioLogado);
        plantaRepository.delete(planta);
    }

    private Planta getPlantaById(Long id) {
        return plantaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Planta não encontrada"));
    }
    private void checkOwnership(Planta planta, Usuario usuarioLogado) {
        boolean isAdmin = usuarioLogado.getRole().equals(Role.ROLE_ADMIN);

        if (planta.getUsuario() == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Planta sem proprietário.");
        }

        boolean isOwner = planta.getUsuario().getIdUsuario() == usuarioLogado.getIdUsuario();


        if (!isAdmin && !isOwner) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado. Você não é o proprietário desta planta.");
        }
    }
}
