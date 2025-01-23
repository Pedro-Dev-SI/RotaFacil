package com.api.rota_facil.services.DTOs;

import com.api.rota_facil.Enums.StatusEntregaEnum;
import com.api.rota_facil.domains.Entrega;

public record EntregaDTO(
        String codigo,
        String nomeCliente,
        String cep,
        String logradouro,
        String numero,
        String bairro,

        String cidade,

        String estado,
        String uf,
        Boolean pereciveis,
        String observacoes,

        String codigoRota,

        StatusEntregaEnum status,

        Double latitude,
        Double longitude
) {

    public static EntregaDTO fromEntity(Entrega entrega) {
        return new EntregaDTO(
                entrega.getCodigo(),
                entrega.getNomeCliente(),
                entrega.getCep(),
                entrega.getLogradouro(),
                entrega.getNumero(),
                entrega.getBairro(),
                entrega.getCidade(),
                entrega.getEstado(),
                entrega.getUf() != null ? entrega.getUf().name() : null,
                entrega.getPereciveis(),
                entrega.getObeservacoes(),
                entrega.getRota() != null ? entrega.getRota().getCodigo() : null,
                entrega.getStatus(),
                entrega.getLatitude(),
                entrega.getLongitude()
        );
    }
}
