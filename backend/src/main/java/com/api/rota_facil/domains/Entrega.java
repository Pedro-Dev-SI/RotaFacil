package com.api.rota_facil.domains;

import com.api.rota_facil.Enums.EstadoEnum;
import com.api.rota_facil.Enums.StatusEntregaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "entrega", schema = "public")
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "uf", nullable = false)
    private EstadoEnum uf;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "pereciveis", nullable = false)
    private Boolean pereciveis;

    @Column(name = "observacoes")
    private String obeservacoes;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEntregaEnum status;
//    @Column(name = "coordenadas_entrega", nullable = false, columnDefinition = "geometry(Point, 4326)")
//    private Point coordenadasEntrega;

    @ManyToOne
    @JoinColumn(name = "rota_id", nullable = false)
    private Rota rota;

    public Entrega() {

    }

//    public void setLocalizacao(double latitude, double longitude) {
//        this.coordenadasEntrega = new org.locationtech.jts.geom.GeometryFactory()
//                .createPoint(new org.locationtech.jts.geom.Coordinate(longitude, latitude));
//    }
//
//    public Double getLatitude() {
//        return this.coordenadasEntrega.getY();
//    }
//
//    public Double getLongitude() {
//        return this.coordenadasEntrega.getX();
//    }
}
