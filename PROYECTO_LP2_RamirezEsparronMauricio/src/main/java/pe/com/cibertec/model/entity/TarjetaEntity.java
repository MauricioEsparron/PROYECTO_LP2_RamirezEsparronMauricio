package pe.com.cibertec.model.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_tarjeta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tarjetaId;

	@Column(name = "numero_tarjeta", nullable = false)
	private String numeroTarjeta;

	@Column(name = "fecha_activacion", nullable = false)
	private LocalDate fechaActivacion;

	@Column(name = "fecha_vencimiento", nullable = false)
	private LocalDate fechaVencimiento;

	@Column(name = "cvv", nullable = false)
	private String cvv;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UsuarioEntity usuario;

	@ManyToOne
	@JoinColumn(name = "tipo_tarjeta_id", nullable = false)
	private TipoTarjetaEntity tipoTarjeta;

	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private EstadoTarjetaEntity estadoTarjeta;
}
