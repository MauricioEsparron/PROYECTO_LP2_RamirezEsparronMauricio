package pe.com.cibertec.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.CuentaEntity;
import pe.com.cibertec.repository.CuentaRepository;
import pe.com.cibertec.service.CuentaService;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	public List<CuentaEntity> buscarCuentas() {
		return cuentaRepository.findAll();
	}

	@Override
	public void crearCuenta(CuentaEntity cuenta) {
		cuentaRepository.save(cuenta);
	}

	@Override
	public CuentaEntity buscarCuentaPorId(Integer id) {
		return cuentaRepository.findById(id).orElse(null);
	}

	@Override
	public void actualizarCuenta(Integer id, CuentaEntity cuentaActualizada) {
		CuentaEntity cuentaEncontrada = buscarCuentaPorId(id);
		if (cuentaEncontrada == null) {
			throw new RuntimeException("Cuenta no encontrada");
		}
		try {
			cuentaEncontrada.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
			cuentaEncontrada.setSaldo(cuentaActualizada.getSaldo());
			cuentaEncontrada.setFechaApertura(cuentaActualizada.getFechaApertura());
			cuentaRepository.save(cuentaEncontrada);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar");
		}
	}

	@Override
	public void eliminarCuenta(Integer id) {
		CuentaEntity cuentaEncontrada = buscarCuentaPorId(id);
		if (cuentaEncontrada == null) {
			throw new RuntimeException("Cuenta no encontrada");
		}
		cuentaRepository.delete(cuentaEncontrada);
	}
}
