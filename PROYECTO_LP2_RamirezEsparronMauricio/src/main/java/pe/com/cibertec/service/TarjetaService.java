package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.TarjetaEntity;

public interface TarjetaService {
    List<TarjetaEntity> buscarTarjetas();
    void crearTarjeta(TarjetaEntity tarjeta);
    TarjetaEntity buscarTarjetaPorId(Integer id);
    void actualizarTarjeta(Integer id, TarjetaEntity tarjetaEntity);
    void eliminarTarjeta(Integer id);
}
