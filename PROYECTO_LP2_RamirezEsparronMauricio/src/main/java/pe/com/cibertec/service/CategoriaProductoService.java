package pe.com.cibertec.service;

import java.util.List;

import pe.com.cibertec.model.entity.CatetgoriaProductoEntity;

public interface CategoriaProductoService {
	List<CatetgoriaProductoEntity> buscarCategoriaProductos();

	void crearCategoriaProducto(CatetgoriaProductoEntity categoriaProducto);

	CatetgoriaProductoEntity buscarCategoriaProductoPorId(Integer id);

	void actualizarCategoriaProducto(Integer id, CatetgoriaProductoEntity catetgoriaProductoEntity);

	void eliminarCategoriaProducto(Integer id);
}
