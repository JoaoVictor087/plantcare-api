package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.request.PlantaDTO;
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

    @PostMapping
    public ResponseEntity<EntityModel<Planta>> criarPlanta(
            @RequestBody @Valid PlantaDTO plantaDTO,
            @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        Planta novaPlanta = plantaService.adicionarPlanta(plantaDTO, usuarioLogado);

        EntityModel<Planta> plantaModel = EntityModel.of(novaPlanta,
                linkTo(methodOn(PlantaController.class).buscarPlantaPorId(Math.toIntExact(novaPlanta.getId()), usuarioLogado)).withSelfRel(),
                linkTo(methodOn(PlantaController.class).listarPlantas(usuarioLogado)).withRel("todas-plantas")
        );

        URI location = linkTo(methodOn(PlantaController.class).buscarPlantaPorId(Math.toIntExact(novaPlanta.getId()), usuarioLogado)).toUri();
        return ResponseEntity.created(location).body(plantaModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Planta>>> listarPlantas(
            @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        List<Planta> plantas = plantaService.listarPlantas(usuarioLogado);

        List<EntityModel<Planta>> plantasModel = plantas.stream()
                .map(planta -> EntityModel.of(planta,
                        linkTo(methodOn(PlantaController.class).buscarPlantaPorId(Math.toIntExact(planta.getId()), usuarioLogado)).withSelfRel(),
                        linkTo(methodOn(PlantaController.class).atualizarPlanta(Math.toIntExact(planta.getId()), null, usuarioLogado)).withRel("update"),
                        linkTo(methodOn(PlantaController.class).deletarPlanta(Math.toIntExact(planta.getId()), usuarioLogado)).withRel("delete")
                ))
                .collect(Collectors.toList());


        CollectionModel<EntityModel<Planta>> collectionModel = CollectionModel.of(plantasModel,
                linkTo(methodOn(PlantaController.class).listarPlantas(usuarioLogado)).withSelfRel()
        );

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Planta>> buscarPlantaPorId(
            @PathVariable Integer id,
            @AuthenticationPrincipal Usuario usuarioLogado
    ) {

        Planta planta = plantaService.buscarPlantaPorId(Long.valueOf(id), usuarioLogado);


        EntityModel<Planta> plantaModel = EntityModel.of(planta,
                linkTo(methodOn(PlantaController.class).buscarPlantaPorId(id, usuarioLogado)).withSelfRel(),
                linkTo(methodOn(PlantaController.class).atualizarPlanta(id, null, usuarioLogado)).withRel("update"),
                linkTo(methodOn(PlantaController.class).deletarPlanta(id, usuarioLogado)).withRel("delete"),
                linkTo(methodOn(PlantaController.class).listarPlantas(usuarioLogado)).withRel("todas-plantas")
        );

        return ResponseEntity.ok(plantaModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Planta>> atualizarPlanta(
            @PathVariable Integer id,
            @RequestBody @Valid PlantaDTO plantaDTO,
            @AuthenticationPrincipal Usuario usuarioLogado
    ) {
        Planta plantaAtualizada = plantaService.atualizarPlanta(Long.valueOf(id), plantaDTO, usuarioLogado);

        EntityModel<Planta> plantaModel = EntityModel.of(plantaAtualizada,
                linkTo(methodOn(PlantaController.class).buscarPlantaPorId(id, usuarioLogado)).withSelfRel(),
                linkTo(methodOn(PlantaController.class).listarPlantas(usuarioLogado)).withRel("todas-plantas")
        );

        return ResponseEntity.ok(plantaModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlanta(
            @PathVariable Integer id,
            @AuthenticationPrincipal Usuario usuarioLogado
    ) {

        plantaService.deletarPlanta(Long.valueOf(id), usuarioLogado);
        return ResponseEntity.noContent().build();
    }
}