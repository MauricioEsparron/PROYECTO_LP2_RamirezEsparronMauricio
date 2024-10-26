package pe.com.cibertec.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_auditoria")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auditoria_id", nullable = false)
	private Integer auditoriaId;

	@Column(name = "accion", nullable = false)
	private String accion; // e.g. "CREAR", "ACTUALIZAR", "ELIMINAR"

	@Column(name = "entidad", nullable = false)
	private String entidad; // e.g. "CUENTA", "USUARIO"

	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha; // fecha y hora de la acción

	@Column(name = "usuario_correo", nullable = false)
	private String usuarioCorreo; // ID del usuario que realiza la acción
}
