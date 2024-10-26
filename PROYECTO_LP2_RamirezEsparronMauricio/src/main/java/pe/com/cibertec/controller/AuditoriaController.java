package pe.com.cibertec.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import pe.com.cibertec.model.entity.AuditoriaEntity;
import pe.com.cibertec.model.entity.CuentaEntity;
import pe.com.cibertec.model.entity.UsuarioEntity;
import pe.com.cibertec.service.AuditoriaService;
import pe.com.cibertec.service.CuentaService;
import pe.com.cibertec.service.PdfService;
import pe.com.cibertec.service.UsuarioService;

@Controller
@RequestMapping("/auditorias")
public class AuditoriaController {

	@Autowired
	private AuditoriaService auditoriaService;

	@Autowired
	private CuentaService cuentaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PdfService pdfService;

	@GetMapping("/")
	public String listarAuditorias(Model model) {
		List<AuditoriaEntity> listaAuditorias = auditoriaService.buscarAuditorias();
		model.addAttribute("lista_aditorias", listaAuditorias);
		return "listar_auditorias";
	}

	@PostMapping("/actualizar_cuenta")
	public String actualizarCuenta(@ModelAttribute CuentaEntity cuenta, Principal principal) {
		// Lógica para actualizar la cuenta
		cuentaService.actualizarCuenta(cuenta.getCuentaId(), cuenta); // Asegúrate de tener el ID en la cuenta

		// Registrar auditoría
		String accion = "ACTUALIZAR";
		String entidad = "CUENTA";
		String usuarioCorreo = obtenerUsuarioCorreo(principal); // Obtener el correo del usuario logueado
		auditoriaService.registrarAuditoria(accion, entidad, usuarioCorreo); // Registrar auditoría con correo

		return "redirect:/cuentas/listar_cuentas"; // Redirigir a la lista de cuentas
	}

	private String obtenerUsuarioCorreo(Principal principal) {
		// Obtenemos el correo directamente desde el Principal
		return principal.getName(); // Esto asume que el nombre del usuario es el correo
	}

	@PostMapping("/crear_cuenta")
	public String crearCuenta(@ModelAttribute CuentaEntity cuenta, Principal principal) {
		// Lógica para crear la cuenta
		cuentaService.crearCuenta(cuenta);

		// Registrar auditoría
		String accion = "CREAR";
		String entidad = "CUENTA";
		String usuarioCorreo = obtenerUsuarioCorreo(principal);
		auditoriaService.registrarAuditoria(accion, entidad, usuarioCorreo);

		return "redirect:/cuentas/listar_cuentas"; // Redirigir a la lista de cuentas
	}

	@GetMapping("/generarAuditorias_pdf")
	public ResponseEntity<InputStreamResource> generarPdf(HttpSession session) throws IOException {
		List<AuditoriaEntity> listaAuditorias = auditoriaService.buscarAuditorias();

		String nombreUsuario = (String) session.getAttribute("usuario");
		if (nombreUsuario == null) {
			nombreUsuario = "Desconocido";
		}

		Map<String, Object> datosPdf = new HashMap<>();
		datosPdf.put("usuarios", listaAuditorias);
		datosPdf.put("nombreUsuario", nombreUsuario);

		ByteArrayInputStream pdfBytes = pdfService.generarPdf("auditorias_pdf", datosPdf);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=auditorias.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pdfBytes));
	}

}
