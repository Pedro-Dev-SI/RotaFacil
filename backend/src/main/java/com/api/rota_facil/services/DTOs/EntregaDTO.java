package com.api.rota_facil.services.DTOs;

public record EntregaDTO(
        String codigo,
        String nomeCliente,
        String cep,
        String logradouro,
        String numero,
        String bairro,
        Boolean pereciveis,
        String observacoes,
        Double latitude,
        Double longitude
) {
}
