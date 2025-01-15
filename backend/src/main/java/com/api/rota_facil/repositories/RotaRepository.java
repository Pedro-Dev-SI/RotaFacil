package com.api.rota_facil.repositories;

import com.api.rota_facil.domains.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RotaRepository extends JpaRepository<Rota, UUID> {

    @Query("SELECT COUNT(r) FROM Rota r WHERE EXTRACT(MONTH FROM r.createdAt) = :mes AND EXTRACT(YEAR FROM r.createdAt) = :ano")
    Long countRotasPorMesEAno(@Param("mes") int mes, @Param("ano") int ano);
}
