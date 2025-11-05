package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.request.CreateSensorDTO;
import com.plantcare.plantcare_api.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/sensor")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;

    @PostMapping("/addSensor")
    public ResponseEntity<?> adicionarSensor(@RequestBody CreateSensorDTO dto, Long id_planta){
        sensorService.criarSensor(dto, id_planta);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/removeSensor")
    public ResponseEntity<?> removerSensor(Long id_sensor){
        sensorService.removerSensor(id_sensor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
