package com.api.rota_facil.controllers;

import com.api.rota_facil.domains.Entrega;
import com.api.rota_facil.services.DTOs.EntregaDTO;
import com.api.rota_facil.services.EntregaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {
    
    private EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }


    @GetMapping()
    public ResponseEntity<List<Entrega>> getAllEntregas() {
        return ResponseEntity.ok(entregaService.getAllEntregas());
    }

    @PostMapping()
    public ResponseEntity<List<Entrega>> createEntregas(@RequestBody List<EntregaDTO> entregas) {
        return ResponseEntity.ok(entregaService.createEntregas(entregas));
    }
}
