package pe.com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.ProductoEntity;
import pe.com.cibertec.repository.ProductoRepository;
import pe.com.cibertec.repository.UsuarioRepository;
import pe.com.cibertec.service.ProductoService;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<ProductoEntity> buscarProductos() {
		return productoRepository.findAll();
	}

	@Override
	public void crearProducto(ProductoEntity producto) {
		productoRepository.save(producto);
	}

	@Override
	public ProductoEntity buscarProductoPorId(Integer id) {
		return productoRepository.findById(id).get();
	}

	@Override
	public void actualizarProducto(Integer id, ProductoEntity productoActualizado) {
		ProductoEntity productoEncontrado = buscarProductoPorId(id);
		if (productoEncontrado == null) {
			throw new RuntimeException("Producto no encontrado");
		}
		try {
			productoEncontrado.setNombre(productoActualizado.getNombre());
			productoEncontrado.setPrecio(productoActualizado.getPrecio());
			productoEncontrado.setStock(productoActualizado.getStock());
			productoEncontrado.setCategoriaProducto(productoActualizado.getCategoriaProducto());

			productoRepository.save(productoEncontrado);
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar", e);
		}
	}

	@Override
	public void eliminarProducto(Integer id) {
		ProductoEntity productoEncontrado = buscarProductoPorId(id);
		if (productoEncontrado == null) {
			throw new RuntimeException("Producto no encontrado");
		}
		productoRepository.delete(productoEncontrado);
	}

}
