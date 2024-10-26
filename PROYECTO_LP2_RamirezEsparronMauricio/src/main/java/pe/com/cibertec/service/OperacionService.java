package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.OperacionEntity;

public interface OperacionService {
    List<OperacionEntity> buscarOperaciones();
    void crearOperacion(OperacionEntity operacion);
    OperacionEntity buscarOperacionPorId(Integer id);
    void actualizarOperacion(Integer id, OperacionEntity operacionEntity);
    void eliminarOperacion(Integer id);
}
