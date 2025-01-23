package com.api.rota_facil.controllers;

import com.api.rota_facil.Enums.StatusEntregaEnum;
import com.api.rota_facil.domains.Entrega;
import com.api.rota_facil.services.DTOs.EntregaDTO;
import com.api.rota_facil.services.EntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
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
    public ResponseEntity<List<EntregaDTO>> createEntregas(@RequestBody List<EntregaDTO> entregas) {
        return ResponseEntity.ok(entregaService.createEntregas(entregas));
    }

    @GetMapping("/find/{status}")
    public ResponseEntity<List<EntregaDTO>> getAllEntregasByStatus(@PathVariable("status") StatusEntregaEnum status ){
        return ResponseEntity.ok(entregaService.getAllByStatus(status));
    }

    @GetMapping("/rota/{codigoRota}")
    public ResponseEntity<List<EntregaDTO>> getAllEntregasByCodigoRota(@PathVariable("codigoRota") String codigoRota) {
        //Teste commit
        return ResponseEntity.ok(entregaService.findAllByCodRota(codigoRota));
    }

    @PostMapping("/iniciar/{codigoRota}")
    public ResponseEntity iniciarRota(@PathVariable("codigoRota") String codigoRota) {
        entregaService.iniciarRota(codigoRota);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/finalizar")
    public ResponseEntity finalizarEntrega(@RequestBody List<String> codsEntrega) {
        entregaService.finalizarEntregas(codsEntrega);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancelar")
    public ResponseEntity cancelarEntregas(@RequestBody List<String> codsEntrega) {
        entregaService.cancelarEntrega(codsEntrega);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/calcular-rota")
    public ResponseEntity<List<EntregaDTO>> calcularRota(@RequestBody List<String> codsEntregas) {
        return ResponseEntity.ok(entregaService.calcularRota(codsEntregas));
    }
}
