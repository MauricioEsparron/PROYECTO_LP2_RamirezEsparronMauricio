package pe.com.cibertec.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.TipoCuentaEntity;
import pe.com.cibertec.repository.TipoCuentaRepository;
import pe.com.cibertec.service.TipoCuentaService;

@Service
@RequiredArgsConstructor
public class TipoCuentaServiceImpl implements TipoCuentaService {

	@Autowired
	private TipoCuentaRepository tipoCuentaRepository;

	@Override
	public List<TipoCuentaEntity> buscarTiposCuenta() {
		return tipoCuentaRepository.findAll();
	}

	@Override
	public void crearTipoCuenta(TipoCuentaEntity tipoCuenta) {
		tipoCuentaRepository.save(tipoCuenta);
	}

	@Override
	public TipoCuentaEntity buscarTipoCuentaPorId(Integer id) {
		return tipoCuentaRepository.findById(id).orElse(null);
	}

	@Override
	public void actualizarTipoCuenta(Integer id, TipoCuentaEntity tipoCuentaActualizado) {
		TipoCuentaEntity tipoCuentaEncontrado = buscarTipoCuentaPorId(id);
		if (tipoCuentaEncontrado == null) {
			throw new RuntimeException("Tipo de cuenta no encontrado");
		}
		try {
			tipoCuentaEncontrado.setDescripcion(tipoCuentaActualizado.getDescripcion());
			tipoCuentaEncontrado.setInteresAnual(tipoCuentaActualizado.getInteresAnual());
			tipoCuentaEncontrado.setComision(tipoCuentaActualizado.getComision());
			tipoCuentaRepository.save(tipoCuentaEncontrado);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar el tipo de cuenta", e);
		}
	}

	@Override
	public void eliminarTipoCuenta(Integer id) {
		TipoCuentaEntity tipoCuentaEncontrado = buscarTipoCuentaPorId(id);
		if (tipoCuentaEncontrado == null) {
			throw new RuntimeException("Tipo de cuenta no encontrado");
		}
		tipoCuentaRepository.delete(tipoCuentaEncontrado);
	}
}
