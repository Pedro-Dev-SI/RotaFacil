package com.api.rota_facil.repositories;

import com.api.rota_facil.Enums.StatusEntregaEnum;
import com.api.rota_facil.domains.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, UUID> {

    List<Entrega> findAllByStatus(StatusEntregaEnum status);

    List<Entrega> findAllByRotaId(UUID rotaId);

    List<Entrega> findAllByCodigoIsIn(List<String> codigos);
}
