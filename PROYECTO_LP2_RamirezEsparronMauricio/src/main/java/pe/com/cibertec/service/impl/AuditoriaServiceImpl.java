package pe.com.cibertec.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.cibertec.model.entity.AuditoriaEntity;
import pe.com.cibertec.repository.AuditoriaRepository;
import pe.com.cibertec.service.AuditoriaService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

	@Autowired
	private AuditoriaRepository auditoriaRepository;

	@Override
	public void registrarAuditoria(String accion, String entidad, String usuarioCorreo) {
		System.out.println("Registrando auditoría: " + accion + ", " + entidad + ", " + usuarioCorreo);
		AuditoriaEntity auditoria = new AuditoriaEntity();
		auditoria.setAccion(accion);
		auditoria.setEntidad(entidad);
		auditoria.setUsuarioCorreo(usuarioCorreo);
		auditoria.setFecha(LocalDateTime.now());
		auditoriaRepository.save(auditoria);
	}

	@Override
	public List<AuditoriaEntity> buscarAuditorias() {
		return auditoriaRepository.findAll();
	}
}
