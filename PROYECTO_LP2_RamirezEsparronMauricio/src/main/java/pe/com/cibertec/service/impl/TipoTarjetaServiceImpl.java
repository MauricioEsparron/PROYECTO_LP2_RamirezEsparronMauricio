package pe.com.cibertec.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.TipoTarjetaEntity;
import pe.com.cibertec.repository.TipoTarjetaRepository;
import pe.com.cibertec.service.TipoTarjetaService;

@Service
@RequiredArgsConstructor
public class TipoTarjetaServiceImpl implements TipoTarjetaService {

    @Autowired
    private TipoTarjetaRepository tipoTarjetaRepository;

    @Override
    public List<TipoTarjetaEntity> buscarTiposTarjeta() {
        return tipoTarjetaRepository.findAll();
    }

    @Override
    public void crearTipoTarjeta(TipoTarjetaEntity tipoTarjeta) {
        tipoTarjetaRepository.save(tipoTarjeta);
    }

    @Override
    public TipoTarjetaEntity buscarTipoTarjetaPorId(Integer id) {
        return tipoTarjetaRepository.findById(id).orElse(null);
    }

    @Override
    public void actualizarTipoTarjeta(Integer id, TipoTarjetaEntity tipoTarjetaActualizado) {
        TipoTarjetaEntity tipoTarjetaEncontrado = buscarTipoTarjetaPorId(id);
        if (tipoTarjetaEncontrado == null) {
            throw new RuntimeException("Tipo de tarjeta no encontrado");
        }
        try {
            tipoTarjetaEncontrado.setDescripcion(tipoTarjetaActualizado.getDescripcion());
            tipoTarjetaEncontrado.setLimiteCredito(tipoTarjetaActualizado.getLimiteCredito());
            tipoTarjetaEncontrado.setIntereses(tipoTarjetaActualizado.getIntereses());
            tipoTarjetaRepository.save(tipoTarjetaEncontrado);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar");
        }
    }

    @Override
    public void eliminarTipoTarjeta(Integer id) {
        TipoTarjetaEntity tipoTarjetaEncontrado = buscarTipoTarjetaPorId(id);
        if (tipoTarjetaEncontrado == null) {
            throw new RuntimeException("Tipo de tarjeta no encontrado");
        }
        tipoTarjetaRepository.delete(tipoTarjetaEncontrado);
    }
}
