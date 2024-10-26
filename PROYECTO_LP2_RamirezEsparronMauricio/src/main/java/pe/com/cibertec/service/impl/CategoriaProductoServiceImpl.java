package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.CatetgoriaProductoEntity;
import pe.com.cibertec.repository.CategoriaProductoRepository;
import pe.com.cibertec.repository.UsuarioRepository;
import pe.com.cibertec.service.CategoriaProductoService;

@Service
@RequiredArgsConstructor
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

	@Autowired
	private CategoriaProductoRepository categoriaProductoRepository;

	@Override
	public List<CatetgoriaProductoEntity> buscarCategoriaProductos() {
		return categoriaProductoRepository.findAll();
	}

	@Override
	public void crearCategoriaProducto(CatetgoriaProductoEntity categoriaProducto) {
		categoriaProductoRepository.save(categoriaProducto);
	}

	@Override
	public CatetgoriaProductoEntity buscarCategoriaProductoPorId(Integer id) {
		return categoriaProductoRepository.findById(id).get();

	}

	@Override
	public void actualizarCategoriaProducto(Integer id, CatetgoriaProductoEntity categoriaActualizada) {
		CatetgoriaProductoEntity categoriaProductoEncontrado = buscarCategoriaProductoPorId(id);
		if (categoriaProductoEncontrado == null) {
			throw new RuntimeException("categoria no encontrada");
		}
		try {
			categoriaProductoEncontrado.setNombre(categoriaActualizada.getNombre());
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar", e);
		}
	}

	@Override
	public void eliminarCategoriaProducto(Integer id) {
		CatetgoriaProductoEntity categoriaProductoEncontrado = buscarCategoriaProductoPorId(id);
		if (categoriaProductoEncontrado == null) {
			throw new RuntimeException("categoria no encontrada");
		}
		categoriaProductoRepository.delete(categoriaProductoEncontrado);
	}
}
