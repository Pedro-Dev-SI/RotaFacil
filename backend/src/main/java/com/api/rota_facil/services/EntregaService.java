package com.api.rota_facil.services;

import com.api.rota_facil.Enums.EstadoEnum;
import com.api.rota_facil.Enums.StatusEntregaEnum;
import com.api.rota_facil.domains.Entrega;
import com.api.rota_facil.domains.Rota;
import com.api.rota_facil.repositories.EntregaRepository;
import com.api.rota_facil.repositories.RotaRepository;
import com.api.rota_facil.services.DTOs.EntregaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntregaService {

    private EntregaRepository entregaRepository;
    private RotaRepository rotaRepository;
    private RotaService rotaService;

    private GeoService geoService;

    public EntregaService(EntregaRepository entregaRepository, RotaRepository rotaRepository, RotaService rotaService, GeoService geoService) {
        this.entregaRepository = entregaRepository;
        this.rotaRepository = rotaRepository;
        this.rotaService = rotaService;
        this.geoService = geoService;
    }

    public List<Entrega> getAllEntregas() {
        return entregaRepository.findAll();
    };

    public List<EntregaDTO> createEntregas(List<EntregaDTO> entregas) {

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
            entregaToBeAdded.setObeservacoes(entrega.observacoes());
            entregaToBeAdded.setStatus(StatusEntregaEnum.CRIADA);

            try {
                entregaToBeAdded.setUf(EstadoEnum.valueOf(entrega.uf().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("UF Inválido: " + entrega.uf());
            }

            entregaToBeAdded.setEstado(entrega.estado());
            entregaToBeAdded.setCidade(entrega.cidade());

            entregaToBeAdded.setRota(rota);
            entregasToSave.add(entregaToBeAdded);
        });

        List<Entrega> savedEntregas = entregaRepository.saveAll(entregasToSave);

        return savedEntregas.stream()
                .map(EntregaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<EntregaDTO> getAllByStatus(StatusEntregaEnum status) {
        return entregaRepository.findAllByStatus(status).stream()
                .map(EntregaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public void iniciarRota(String codigoRota) {
        Rota rota = rotaRepository.findByCodigo(codigoRota);
        List<Entrega> entregasToBeUpdated = entregaRepository.findAllByRotaId(rota.getId());

        entregasToBeUpdated.forEach(entrega -> {
            entrega.setStatus(StatusEntregaEnum.EM_ANDAMENTO);
        });

        entregaRepository.saveAll(entregasToBeUpdated);
    }

    public void finalizarEntregas(List<String> codsEntrega) {

        List<Entrega> entregasToBeFineshed = entregaRepository.findAllByCodigoIsIn(codsEntrega);

        entregasToBeFineshed.forEach(entrega -> {
            entrega.setStatus(StatusEntregaEnum.FINALIZADA);
        });

        entregaRepository.saveAll(entregasToBeFineshed);

    }

    public void cancelarEntrega(List<String> codsEntrega) {

        List<Entrega> entregasToBeCancelled = entregaRepository.findAllByCodigoIsIn(codsEntrega);

        entregasToBeCancelled.forEach(entrega -> {
            entrega.setStatus(StatusEntregaEnum.CANCELADA);
        });

        entregaRepository.saveAll(entregasToBeCancelled);

    }
}
