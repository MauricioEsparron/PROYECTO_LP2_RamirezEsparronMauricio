package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.ProductoEntity;

public interface ProductoService {
	List<ProductoEntity> buscarProductos();

	void crearProducto(ProductoEntity producto);

	ProductoEntity buscarProductoPorId(Integer id);

	void actualizarProducto(Integer id, ProductoEntity productoEntity);

	void eliminarProducto(Integer id);

}
