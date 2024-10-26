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
import pe.com.cibertec.model.entity.CuentaEntity;
import pe.com.cibertec.model.entity.EstadoCuentaEntity;
import pe.com.cibertec.model.entity.TipoCuentaEntity;
import pe.com.cibertec.model.entity.UsuarioEntity;
import pe.com.cibertec.service.CuentaService;
import pe.com.cibertec.service.EstadoCuentaService;
import pe.com.cibertec.service.PdfService;
import pe.com.cibertec.service.TipoCuentaService;
import pe.com.cibertec.service.UsuarioService;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;

	@Autowired
	private TipoCuentaService tipoCuentaService;

	@Autowired
	private EstadoCuentaService estadoCuentaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PdfService pdfService;

	@GetMapping("/")
	public String listarCuentas(Model model) {
		List<CuentaEntity> listaCuentas = cuentaService.buscarCuentas();
		model.addAttribute("lista_cuentas", listaCuentas);
		return "listar_cuentas";
	}

	@GetMapping("/registrar_cuenta")
	public String mostrarRegistrarCuenta(Model model) {
		model.addAttribute("cuenta", new CuentaEntity());
		List<UsuarioEntity> listaUsuario = usuarioService.buscarUsuarios();
		model.addAttribute("listarUsuario", listaUsuario);
		List<TipoCuentaEntity> listaTipoCuenta = tipoCuentaService.buscarTiposCuenta();
		model.addAttribute("listarTipoCuenta", listaTipoCuenta);
		List<EstadoCuentaEntity> listaEstadoCuenta = estadoCuentaService.buscarEstadoCuentas();
		model.addAttribute("listarEstadoCuenta", listaEstadoCuenta);

		return "registrar_cuenta";
	}

	@PostMapping("/registrar_cuenta")
	public String registrarCuenta(@ModelAttribute("cuenta") CuentaEntity cuenta, Model model) {
		cuentaService.crearCuenta(cuenta);
		return "redirect:/cuentas/";
	}

	@GetMapping("/detalle_cuenta/{id}")
	public String verDetalleCuenta(@PathVariable("id") Integer id, Model model) {
		CuentaEntity cuenta = cuentaService.buscarCuentaPorId(id);
		model.addAttribute("cuenta", cuenta);
		return "detalle_cuenta";
	}

	@GetMapping("/delete_cuenta/{id}")
	public String deleteCuenta(@PathVariable("id") Integer id) {
		cuentaService.eliminarCuenta(id);
		return "redirect:/cuentas/";
	}

	@GetMapping("/editar_cuenta/{id}")
	public String mostrarActualizarCuenta(@PathVariable("id") Integer id, Model model) {
		CuentaEntity cuenta = cuentaService.buscarCuentaPorId(id);
		model.addAttribute("cuenta", cuenta);
		return "editar_cuenta";
	}

	@PostMapping("/editar_cuenta/{id}")
	public String actualizarCuenta(@PathVariable("id") Integer id, @ModelAttribute("cuenta") CuentaEntity cuenta) {
		cuentaService.actualizarCuenta(id, cuenta);
		return "redirect:/cuentas/";
	}

	@GetMapping("/generarCuenta_pdf")
	public ResponseEntity<InputStreamResource> generarPdf(HttpSession session) throws IOException {
		List<CuentaEntity> listaCuentas = cuentaService.buscarCuentas();

		String nombreUsuario = (String) session.getAttribute("usuario");
		if (nombreUsuario == null) {
			nombreUsuario = "Desconocido";
		}

		Map<String, Object> datosPdf = new HashMap<>();
		datosPdf.put("cuentas", listaCuentas);
		datosPdf.put("nombreUsuario", nombreUsuario);

		ByteArrayInputStream pdfBytes = pdfService.generarPdf("cuentas_pdf", datosPdf);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=cuentas.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pdfBytes));
	}
}
