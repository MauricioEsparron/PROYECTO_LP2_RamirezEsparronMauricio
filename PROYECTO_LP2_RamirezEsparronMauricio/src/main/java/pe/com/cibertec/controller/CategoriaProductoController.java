package pe.com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.CatetgoriaProductoEntity;
import pe.com.cibertec.service.CategoriaProductoService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categoriaproductos")
public class CategoriaProductoController {

	@Autowired
	private CategoriaProductoService categoriaProductoService;

	@GetMapping("/")
	public String listarCategoriaProductos(Model model) {
		List<CatetgoriaProductoEntity> listaCategoriaProductos = categoriaProductoService.buscarCategoriaProductos();
		model.addAttribute("lista_categoriaproductos", listaCategoriaProductos);
		return "listar_categoriaproductos";
	}

	@GetMapping("/registrar_categoriaproducto")
	public String mostrarRegistrarCategoriaProducto(Model model) {
		model.addAttribute("categoriaProducto", new CatetgoriaProductoEntity());
		List<CatetgoriaProductoEntity> listaCategoriaProductos = categoriaProductoService.buscarCategoriaProductos();
		model.addAttribute("listarCategoriaProductos", listaCategoriaProductos);
		return "registrar_categoriaproducto";
	}

	@PostMapping("/registrar_categoriaproducto")
	public String registrarCategoriaProducto(
			@ModelAttribute("categoriaproducto") CatetgoriaProductoEntity catetgoriaProductoEntity, Model model) {
		categoriaProductoService.crearCategoriaProducto(catetgoriaProductoEntity);
		return "registrar_categoriaproducto";
	}

	@GetMapping("/detalle_categoriaproducto")
	public String verDetalleCategoriaProducto(Model model, @PathVariable("id") Integer id) {
		CatetgoriaProductoEntity categoriaProducto = categoriaProductoService.buscarCategoriaProductoPorId(id);
		model.addAttribute("categoriaProducto", categoriaProducto);
		return "detalle_categoriaproducto";
	}

	@GetMapping("/editar_categoriaproducto/{id}")
	public String mostrarActualizarCategoriaProducto(@PathVariable("id") Integer id, Model model) {
		CatetgoriaProductoEntity categoriaProducto = categoriaProductoService.buscarCategoriaProductoPorId(id);
		model.addAttribute("categoriaProducto", categoriaProducto);
		List<CatetgoriaProductoEntity> listaCategoriaProductos = categoriaProductoService.buscarCategoriaProductos();
		model.addAttribute("listarCategoriaProductos", listaCategoriaProductos);
		return "editar_categoriaproducto";
	}

	@PostMapping("/editar_categoriaproducto/{id}")
	public String actualizarCategoriaProducto(@PathVariable("id") Integer id,
			@ModelAttribute("categoriaProducto") CatetgoriaProductoEntity categoriaProducto) {
		categoriaProductoService.actualizarCategoriaProducto(id, categoriaProducto);
		return "redirect:/categoriaproductos/";
	}

	@GetMapping("/delete/{id}")
	public String eliminarCategoriaProducto(@PathVariable("id") Integer id) {
		categoriaProductoService.eliminarCategoriaProducto(id);
		return "redirect:/categoroaproductos/";
	}
}
