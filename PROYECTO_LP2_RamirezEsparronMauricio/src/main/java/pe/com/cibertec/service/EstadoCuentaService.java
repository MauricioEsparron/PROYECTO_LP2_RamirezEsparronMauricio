package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.EstadoCuentaEntity;

public interface EstadoCuentaService {

	List<EstadoCuentaEntity> buscarEstadoCuentas();

	void crearEstadoCuenta(EstadoCuentaEntity estadocuenta);

	EstadoCuentaEntity buscarEstadoCuentaPorId(Integer id);

	void actualizarEstadoCuenta(Integer id, EstadoCuentaEntity estadocuentaEntity);

	void eliminarEstadoCuenta(Integer id);
}
