package com.plantcare.plantcare_api.service;

import com.plantcare.plantcare_api.DTOs.request.LeituraSensorDTO;
import com.plantcare.plantcare_api.entities.Leitura;
import com.plantcare.plantcare_api.repositories.LeituraRepository;
import com.plantcare.plantcare_api.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeituraService {
    private final LeituraRepository leituraRepository;
    private final SensorRepository sensorRepository;

    public Leitura novaLeitura(LeituraSensorDTO dto){
        Leitura leitura = new Leitura();
        leitura.setDataLeitura(LocalDateTime.now());
        leitura.setSensor(sensorRepository.findSensorById(dto.id_sensor()));
        leitura.setValor(dto.valor());

        leituraRepository.save(leitura);
        return leitura;
    }
}
