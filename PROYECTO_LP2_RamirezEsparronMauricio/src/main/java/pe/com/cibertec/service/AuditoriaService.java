package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.AuditoriaEntity;

public interface AuditoriaService {
	void registrarAuditoria(String accion, String entidad, String usuarioCorreo);

	List<AuditoriaEntity> buscarAuditorias();
}
