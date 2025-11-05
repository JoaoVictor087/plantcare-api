package com.plantcare.plantcare_api.service;

import com.plantcare.plantcare_api.DTOs.request.CreateSensorDTO;
import com.plantcare.plantcare_api.entities.Sensor;
import com.plantcare.plantcare_api.exceptions.SensorException;
import com.plantcare.plantcare_api.repositories.PlantaRepository;
import com.plantcare.plantcare_api.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SensorService {
    private final SensorRepository sensorRepository;
    private final PlantaRepository plantaRepository;

    public Sensor criarSensor(CreateSensorDTO dto){
        Sensor sensor = new Sensor();
        sensor.setSensor(dto.tipoSensor());
        sensor.setPlanta(plantaRepository.findPlantaById(dto.id_planta()));

        sensorRepository.save(sensor);
        return sensor;
        //por enquanto sem limites de sensores
    }

    public void removerSensor(Long id_sensor){
        if(!sensorRepository.existsById(id_sensor)){
            log.debug("Sensor não existe");
            throw new SensorException("Sensor não existe");
        }
        sensorRepository.deleteById(id_sensor);
        log.debug("Sensor de id: {} removido com sucesso", id_sensor);
    }

    public List<Sensor> listarSensores(Long id_planta){
        return sensorRepository.findByPlantaId(id_planta);
    }

}
