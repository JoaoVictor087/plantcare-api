package com.plantcare.plantcare_api.controller;

import com.plantcare.plantcare_api.DTOs.request.LeituraSensorDTO;
import com.plantcare.plantcare_api.service.LeituraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/leitura")
@RequiredArgsConstructor
public class LeituraController {
    private final LeituraService leituraService;

    @PostMapping
    public ResponseEntity<?> novaLeitura(@RequestBody LeituraSensorDTO dto){
        leituraService.novaLeitura(dto);
        return ResponseEntity.status(201).build();
    }
}
