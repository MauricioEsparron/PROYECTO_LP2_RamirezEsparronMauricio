package pe.com.cibertec.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.CatetgoriaProductoEntity;
import pe.com.cibertec.model.entity.ProductoEntity;
import pe.com.cibertec.service.CategoriaProductoService;
import pe.com.cibertec.service.ProductoService;
import pe.com.cibertec.service.PdfService; // Asegúrate de tener un servicio para manejar PDF

@Controller
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private CategoriaProductoService categoriaProductoService;

	@Autowired
	private PdfService pdfService;

	@GetMapping("/")
	public String listarProductos(Model model, HttpSession session) {
		String usuarioNombre = (String) session.getAttribute("usuario");
		model.addAttribute("usuario", usuarioNombre);

		List<ProductoEntity> listaProductos = productoService.buscarProductos();
		model.addAttribute("lista_productos", listaProductos);
		return "listar_productos";
	}

	@GetMapping("/registrar_producto")
	public String mostrarRegistrarProducto(Model model) {
		model.addAttribute("producto", new ProductoEntity());
		List<CatetgoriaProductoEntity> listaCategoriaProductos = categoriaProductoService.buscarCategoriaProductos();
		model.addAttribute("listarCategoriaProducto", listaCategoriaProductos);
		return "registrar_producto";
	}

	@PostMapping("/registrar_producto")
	public String registrarProducto(@ModelAttribute("producto") ProductoEntity productoEntity) {
		productoService.crearProducto(productoEntity);
		return "redirect:/productos/";
	}

	@GetMapping("/detalle_producto/{id}")
	public String verDetalleProducto(Model model, @PathVariable("id") Integer id) {
		ProductoEntity producto = productoService.buscarProductoPorId(id);
		model.addAttribute("producto", producto);
		return "detalle_producto";
	}

	@GetMapping("/editar_producto/{id}")
	public String mostrarActualizarProducto(@PathVariable("id") Integer id, Model model) {
		ProductoEntity producto = productoService.buscarProductoPorId(id);
		model.addAttribute("producto", producto);
		List<CatetgoriaProductoEntity> listaCategoriaProductos = categoriaProductoService.buscarCategoriaProductos();
		model.addAttribute("listarCategoriaProductos", listaCategoriaProductos);
		return "editar_producto";
	}

	@PostMapping("/editar_producto/{id}")
	public String actualizarProducto(@PathVariable("id") Integer id,
			@ModelAttribute("producto") ProductoEntity producto) {
		productoService.actualizarProducto(id, producto);
		return "redirect:/productos/";
	}

	@GetMapping("/delete/{id}")
	public String eliminarProducto(@PathVariable("id") Integer id) {
		try {
			productoService.eliminarProducto(id);
		} catch (Exception e) {
		}
		return "redirect:/productos/";
	}

	@GetMapping("/generarProducto_pdf")
	public ResponseEntity<InputStreamResource> generarPdf(HttpSession session) throws IOException {
		List<ProductoEntity> listaProductos = productoService.buscarProductos();

		// Captura el nombre del usuario de la sesión
		String nombreUsuario = (String) session.getAttribute("usuario");
		if (nombreUsuario == null) {
			nombreUsuario = "Desconocido"; // O un valor predeterminado
		}

		Map<String, Object> datosPdf = new HashMap<>();
		datosPdf.put("productos", listaProductos);
		datosPdf.put("nombreUsuario", nombreUsuario); // Añadir el nombre del usuario

		ByteArrayInputStream pdfBytes = pdfService.generarPdf("template_pdf", datosPdf);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=productos.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pdfBytes));
	}
}
