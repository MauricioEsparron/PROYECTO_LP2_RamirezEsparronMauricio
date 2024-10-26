package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.TipoCuentaEntity;

public interface TipoCuentaService {
	List<TipoCuentaEntity> buscarTiposCuenta();

	void crearTipoCuenta(TipoCuentaEntity tipoCuenta);

	TipoCuentaEntity buscarTipoCuentaPorId(Integer id);

	void actualizarTipoCuenta(Integer id, TipoCuentaEntity tipoCuentaEntity);

	void eliminarTipoCuenta(Integer id);
}
