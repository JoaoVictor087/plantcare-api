package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.request.CreateSensorDTO;
import com.plantcare.plantcare_api.entities.Sensor;
import com.plantcare.plantcare_api.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/sensor")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;

    @PostMapping("/addSensor")
    public ResponseEntity<?> adicionarSensor(@RequestBody CreateSensorDTO dto){
        sensorService.criarSensor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/removeSensor")
    public ResponseEntity<?> removerSensor(@RequestBody Long id_sensor){
        sensorService.removerSensor(id_sensor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/listarSensores")
    public ResponseEntity<?> listarSensores(@RequestBody Long id_planta){
        List<Sensor> listarSensores = sensorService.listarSensores(id_planta);
        return ResponseEntity.ok().body(listarSensores);
    }
}
