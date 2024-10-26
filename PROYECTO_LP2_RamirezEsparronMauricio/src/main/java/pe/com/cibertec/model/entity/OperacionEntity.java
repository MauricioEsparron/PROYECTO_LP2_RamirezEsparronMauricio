package pe.com.cibertec.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_operacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperacionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer operacion_id;

	@ManyToOne
	@JoinColumn(name = "tipo_operacion_id", nullable = false)
	private TipoOperacionEntity tipoOperacion;

	@Column(name = "monto", nullable = false)
	private Double monto;

	@Column(name = "fecha_hora", nullable = false)
	private LocalDateTime fechaHora;

	@ManyToOne
	@JoinColumn(name = "cuenta_origen_id", nullable = true)
	private CuentaEntity cuentaOrigen;

	@ManyToOne
	@JoinColumn(name = "cuenta_destino_id", nullable = true)
	private CuentaEntity cuentaDestino;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UsuarioEntity usuario;

	@Column(name = "estado")
	private String estado; // ej. "completada", "pendiente", "fallida"
}
