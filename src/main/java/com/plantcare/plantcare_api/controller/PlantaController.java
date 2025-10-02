package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.PlantaDTO;
import com.plantcare.plantcare_api.entities.Planta;
import com.plantcare.plantcare_api.service.PlantaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planta")
@RequiredArgsConstructor
public class PlantaController {
    private static final Logger log = LoggerFactory.getLogger(PlantaController.class);
    private final PlantaService plantaService;

    @PostMapping("/{id_usuario}/adicionarPlanta")
    public ResponseEntity<?>adicionarPlanta(@RequestBody PlantaDTO dto, @PathVariable long id_usuario){
        try{
            Planta planta = plantaService.adicionarPlanta(dto, id_usuario);
            log.info("Planta adicionada com sucesso ao usuário: " + planta.getUsuario().getNomeUsuario());
            return new ResponseEntity<>("Planta adicionada com sucesso",HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Erro intero do servidor");
            log.error(e.getMessage());
            return new ResponseEntity<>("Erro interno do servidor do servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/atualizarPlanta/{id_planta}")
    public ResponseEntity<?>atualizarPlanta(@RequestBody PlantaDTO dto, @PathVariable long id_planta){
        try {
            plantaService.atualizarPlanta(dto,id_planta);
            log.info("Planta atualizada com sucesso");
            return new ResponseEntity<>("Planta atualizada com sucesso", HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            log.error(e.getMessage());
            return new ResponseEntity<>("Planta não existe no banco de dados", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listarPlantas/{id_usuario}")
    public ResponseEntity<?>listarPlantas(@PathVariable long id_usuario, Pageable pageable){
        try {
            Page<Planta> listaDePlantas = plantaService.listarPlantasDoUsuario(pageable, id_usuario);
            log.info("Retornado lista de plantas do usuário");
            return new ResponseEntity<>(listaDePlantas, HttpStatus.OK);
        }catch (Exception e){
            log.error("Erro interno do Servidor");
            log.error(e.getMessage());
            return new ResponseEntity<>("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deletarPlanta/{id_planta}")
    public ResponseEntity<?>deletarPlataPorId(@PathVariable long id_planta){
        try {
            plantaService.deletarPlantaPorId(id_planta);
            return new ResponseEntity<>("Planta deletada com sucesso", HttpStatus.OK);
        }catch (NullPointerException e){
            log.error("Planta não encontrada no banco de dados");
            log.error(e.getMessage());
            return new ResponseEntity<>("Planta não encontrada no banco de dados", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error("Erro interno do servidor");
            log.error(e.getMessage());
            return new ResponseEntity<>("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
