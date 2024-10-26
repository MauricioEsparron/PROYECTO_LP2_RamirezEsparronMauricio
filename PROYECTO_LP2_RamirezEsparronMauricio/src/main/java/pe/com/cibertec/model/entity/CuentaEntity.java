package pe.com.cibertec.model.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_cuenta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cuenta_id", nullable = false)
	private Integer cuentaId;

	@Column(name = "numero_cuenta", nullable = false)
	private String numeroCuenta;

	@Column(name = "saldo", nullable = false)
	private Double saldo;

	@Column(name = "fecha_apertura", nullable = false)
	private LocalDate fechaApertura;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UsuarioEntity usuario;

	@ManyToOne
	@JoinColumn(name = "tipo_cuenta_id", nullable = false)
	private TipoCuentaEntity tipoCuenta;

	@ManyToOne
	@JoinColumn(name = "estado_cuenta", nullable = false)
	private EstadoCuentaEntity estadoCuenta;
}
