package pe.com.cibertec.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_tipoOperacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoOperacionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tipoOperacion_id;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;
}
