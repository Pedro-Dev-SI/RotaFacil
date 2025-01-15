package com.api.rota_facil.services;

import com.api.rota_facil.Enums.StatusEntregaEnum;
import com.api.rota_facil.domains.Entrega;
import com.api.rota_facil.domains.Rota;
import com.api.rota_facil.repositories.EntregaRepository;
import com.api.rota_facil.services.DTOs.EntregaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntregaService {

    private EntregaRepository entregaRepository;
    private RotaService rotaService;

    public EntregaService(EntregaRepository entregaRepository, RotaService rotaService) {
        this.entregaRepository = entregaRepository;
        this.rotaService = rotaService;
    }

    public List<Entrega> getAllEntregas() {
        return entregaRepository.findAll();
    };

    public List<Entrega> createEntregas(List<EntregaDTO> entregas) {

        Rota rota = rotaService.createRota();

        List<Entrega> entregasToSave = new ArrayList<>();

        entregas.forEach(entrega -> {
            Entrega entregaToBeAdded = new Entrega();
            entregaToBeAdded.setCodigo(rota.getCodigo() + "-" + entrega.cep());
            entregaToBeAdded.setNomeCliente(entrega.nomeCliente());
            entregaToBeAdded.setCep(entrega.cep());
            entregaToBeAdded.setLogradouro(entrega.logradouro());
            entregaToBeAdded.setNumero(entrega.numero());
            entregaToBeAdded.setBairro(entrega.bairro());
            entregaToBeAdded.setPereciveis(entrega.pereciveis());
            entregaToBeAdded.setObeservacoes(entrega.observacoes() != null ? entrega.observacoes() : "SEM OBESERVAÇÕES");
            entregaToBeAdded.setStatus(StatusEntregaEnum.CRIADA);
            entregaToBeAdded.setLocalizacao(entrega.latitude(), entrega.longitude());
            entregaToBeAdded.setRota(rota);
            entregasToSave.add(entregaToBeAdded);
        });

        return entregaRepository.saveAll(entregasToSave);
    }

}
