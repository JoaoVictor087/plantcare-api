package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.PlantaDTO;
import com.plantcare.plantcare_api.entities.Planta;
import com.plantcare.plantcare_api.service.PlantaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planta")
@RequiredArgsConstructor
public class PlantaController {
    private static final Logger log = LoggerFactory.getLogger(PlantaController.class);
    private final PlantaService plantaService;

    @PostMapping("/{id}/adicionarPlanta")
    public ResponseEntity<?>adicionarPlanta(@RequestBody PlantaDTO dto, @PathVariable long id){
        try{
            Planta planta = plantaService.adicionarPlanta(dto, id);
            log.info("Planta adicionada com sucesso ao usuário: " + planta.getUsuario().getNomeUsuario());
            return new ResponseEntity<>("Planta adicionada com sucesso",HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Erro intero do servidor");
            log.error(e.getMessage());
            return new ResponseEntity<>("Erro interno do servidor do servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
