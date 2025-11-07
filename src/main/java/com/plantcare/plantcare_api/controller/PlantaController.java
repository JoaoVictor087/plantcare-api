package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.request.PlantaDTO;
import com.plantcare.plantcare_api.DTOs.response.PlantaResponseDTO;
import com.plantcare.plantcare_api.entities.Planta;
import com.plantcare.plantcare_api.entities.Usuario;
import com.plantcare.plantcare_api.service.PlantaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/plantas")
@RequiredArgsConstructor
public class PlantaController {

    private final PlantaService plantaService;

    private PlantaResponseDTO toResponseDTO(Planta planta) {
        return new PlantaResponseDTO(
                planta.getId(),
                planta.getNome(),
                planta.getEspecie(),
                planta.getDataCadastro(),
                planta.getDataAtualizacao(),
                planta.getImgLink(),
                planta.getUmidade(),
                planta.getTemperatura(),
                planta.getStatus()
        );
    }

    @PostMapping
    public ResponseEntity<?> criarPlanta(@RequestBody @Valid PlantaDTO plantaDTO,
                                         @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        Planta novaPlanta = plantaService.adicionarPlanta(plantaDTO, usuarioLogado);

        PlantaResponseDTO responseDTO = toResponseDTO(novaPlanta);

        EntityModel<PlantaResponseDTO> plantaModel = EntityModel.of(responseDTO,
                linkTo(methodOn(PlantaController.class).buscarPlantaPorId(novaPlanta.getId(), usuarioLogado)).withSelfRel(),
                linkTo(methodOn(PlantaController.class).listarPlantas(usuarioLogado)).withRel("todas-plantas")
        );

        URI location = linkTo(methodOn(PlantaController.class).buscarPlantaPorId(novaPlanta.getId(), usuarioLogado)).toUri();
        return ResponseEntity.created(location).body(plantaModel);
    }

    @GetMapping
    public ResponseEntity<?> listarPlantas(@AuthenticationPrincipal Usuario usuarioLogado){
        List<Planta> plantas = plantaService.listarPlantas(usuarioLogado);

        List<EntityModel<PlantaResponseDTO>> plantasModel = plantas.stream()
                .map(planta -> {
                    PlantaResponseDTO dto = toResponseDTO(planta);
                    return EntityModel.of(dto,
                            linkTo(methodOn(PlantaController.class).buscarPlantaPorId(planta.getId(), usuarioLogado)).withSelfRel(),
                            linkTo(methodOn(PlantaController.class).atualizarPlanta(planta.getId(), null, usuarioLogado)).withRel("update"),
                            linkTo(methodOn(PlantaController.class).deletarPlanta(planta.getId(), usuarioLogado)).withRel("delete")
                    );
                })
                .collect(Collectors.toList());

        CollectionModel<EntityModel<PlantaResponseDTO>> collectionModel = CollectionModel.of(plantasModel,
                linkTo(methodOn(PlantaController.class).listarPlantas(usuarioLogado)).withSelfRel()
        );

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPlantaPorId(@PathVariable Long id,
                                               @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        Planta planta = plantaService.buscarPlantaPorId(id, usuarioLogado);

        PlantaResponseDTO responseDTO = toResponseDTO(planta);

        EntityModel<PlantaResponseDTO> plantaModel = EntityModel.of(responseDTO,
                linkTo(methodOn(PlantaController.class).buscarPlantaPorId(id, usuarioLogado)).withSelfRel(),
                linkTo(methodOn(PlantaController.class).atualizarPlanta(id, null, usuarioLogado)).withRel("update"),
                linkTo(methodOn(PlantaController.class).deletarPlanta(id, usuarioLogado)).withRel("delete"),
                linkTo(methodOn(PlantaController.class).listarPlantas(usuarioLogado)).withRel("todas-plantas")
        );

        return ResponseEntity.ok(plantaModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPlanta(@PathVariable Long id,
                                             @RequestBody @Valid PlantaDTO plantaDTO,
                                             @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        Planta plantaAtualizada = plantaService.atualizarPlanta(id, plantaDTO, usuarioLogado);

        PlantaResponseDTO responseDTO = toResponseDTO(plantaAtualizada);

        EntityModel<PlantaResponseDTO> plantaModel = EntityModel.of(responseDTO,
                linkTo(methodOn(PlantaController.class).buscarPlantaPorId(id, usuarioLogado)).withSelfRel(),
                linkTo(methodOn(PlantaController.class).listarPlantas(usuarioLogado)).withRel("todas-plantas")
        );

        return ResponseEntity.ok(plantaModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlanta(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        plantaService.deletarPlanta(id, usuarioLogado);
        return ResponseEntity.noContent().build();
    }
}