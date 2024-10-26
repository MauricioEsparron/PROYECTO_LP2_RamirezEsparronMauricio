package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cibertec.model.entity.EstadoCuentaEntity;
import pe.com.cibertec.repository.EstadoCuentaRepository;
import pe.com.cibertec.service.EstadoCuentaService;

@Service
public class EstadoCuentaServiceImpl implements EstadoCuentaService {

	@Autowired
	EstadoCuentaRepository estadoCuentaRepository;

	@Override
	public List<EstadoCuentaEntity> buscarEstadoCuentas() {
		return estadoCuentaRepository.findAll();
	}

	@Override
	public void crearEstadoCuenta(EstadoCuentaEntity estadocuenta) {
		estadoCuentaRepository.save(estadocuenta);
	}

	@Override
	public EstadoCuentaEntity buscarEstadoCuentaPorId(Integer id) {
		return estadoCuentaRepository.findById(id).orElse(null);
	}

	@Override
	public void actualizarEstadoCuenta(Integer id, EstadoCuentaEntity estadocuentaActualizado) {
		EstadoCuentaEntity estadoCuentaEncontrado = buscarEstadoCuentaPorId(id);
		if (estadoCuentaEncontrado == null) {
			throw new RuntimeException("Estado cuenta no encontrado");
		}
		try {
			estadoCuentaEncontrado.setDescripcion(estadocuentaActualizado.getDescripcion());
			estadoCuentaRepository.save(estadoCuentaEncontrado);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar el estado cuenta", e);
		}
	}

	@Override
	public void eliminarEstadoCuenta(Integer id) {
		EstadoCuentaEntity estadoCuentaEncontrado = buscarEstadoCuentaPorId(id);
		if (estadoCuentaEncontrado == null) {
			throw new RuntimeException("Estado cuenta no encontrado");
		}
		estadoCuentaRepository.delete(estadoCuentaEncontrado);
	}

}
