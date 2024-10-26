package pe.com.cibertec.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cibertec.model.entity.OperacionEntity;
import pe.com.cibertec.repository.OperacionRepository; // Cambiar a OperacionRepository
import pe.com.cibertec.service.OperacionService; // Usar el nombre correcto de la interfaz

@Service
public class OperacionServiceImpl implements OperacionService {

	@Autowired
	private OperacionRepository operacionRepository;

	@Override
	public List<OperacionEntity> buscarOperaciones() {
		return operacionRepository.findAll();
	}

	@Override
	public void crearOperacion(OperacionEntity operacion) {
		operacionRepository.save(operacion);
	}

	@Override
	public OperacionEntity buscarOperacionPorId(Integer id) {
		return operacionRepository.findById(id).orElse(null);
	}

	@Override
	public void actualizarOperacion(Integer id, OperacionEntity operacionActualizada) {
		OperacionEntity operacionEncontrada = buscarOperacionPorId(id);
		if (operacionEncontrada == null) {
			throw new RuntimeException("Operación no encontrada");
		}
		try {
			operacionEncontrada.setTipoOperacion(operacionActualizada.getTipoOperacion());
			operacionEncontrada.setMonto(operacionActualizada.getMonto());
			operacionEncontrada.setFechaHora(operacionActualizada.getFechaHora());
			operacionEncontrada.setCuentaOrigen(operacionActualizada.getCuentaOrigen());
			operacionEncontrada.setCuentaDestino(operacionActualizada.getCuentaDestino());
			operacionEncontrada.setUsuario(operacionActualizada.getUsuario());
			operacionEncontrada.setEstado(operacionActualizada.getEstado());
			operacionRepository.save(operacionEncontrada);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar: " + e.getMessage());
		}
	}

	@Override
	public void eliminarOperacion(Integer id) {
		OperacionEntity operacionEncontrada = buscarOperacionPorId(id);
		if (operacionEncontrada == null) {
			throw new RuntimeException("Operación no encontrada");
		}
		operacionRepository.delete(operacionEncontrada);
	}
}
