package pe.com.cibertec.model.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tipo_tarjeta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoTarjetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_tarjeta_id")
    private Integer tipoTarjetaId;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "limite_credito")
    private Double limiteCredito; // Solo para tarjetas de crédito

    @Column(name = "intereses")
    private Double intereses; // Solo para tarjetas de crédito
    
    // Relación bidireccional con TarjetaEntity
    @OneToMany(mappedBy = "tipoTarjeta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TarjetaEntity> tarjetas;
}
