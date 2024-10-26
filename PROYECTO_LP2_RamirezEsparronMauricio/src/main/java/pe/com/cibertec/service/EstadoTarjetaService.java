package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.EstadoTarjetaEntity;

public interface EstadoTarjetaService {
	List<EstadoTarjetaEntity> buscarEstadoTarjetas();

	void crearEstadoTarjeta(EstadoTarjetaEntity estadotarjeta);

	EstadoTarjetaEntity buscarEstadoTarjetaPorId(Integer id);

	void actualizarEstadoTarjeta(Integer id, EstadoTarjetaEntity estadotarjetaEntity);

	void eliminarEstadoTarjeta(Integer id);

}
