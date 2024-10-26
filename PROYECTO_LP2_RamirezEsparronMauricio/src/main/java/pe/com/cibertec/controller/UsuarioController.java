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
import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.UsuarioEntity;
import pe.com.cibertec.service.PdfService;
import pe.com.cibertec.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioService usuarioService;

	@Autowired
	private PdfService pdfService;

	@GetMapping("/")
	public String listarUsuarios(Model model) {
		List<UsuarioEntity> listaUsuarios = usuarioService.buscarUsuarios();
		model.addAttribute("lista_usuarios", listaUsuarios);
		return "listar_usuarios";
	}

	@GetMapping("/registrar_usuario")
	public String mostrarRegistrarUsuario(Model model) {
		model.addAttribute("usuario", new UsuarioEntity());
		return "registrar_usuario";
	}

	@PostMapping("/registrar_usuario")
	public String registrarUsuario(@ModelAttribute("usuario") UsuarioEntity usuarioFormulario,
			@RequestParam("foto") MultipartFile foto, Model model) {
		usuarioService.crearUsuario(usuarioFormulario, foto);
		return "redirect:/login";
	}

	@GetMapping("/generarUsuario_pdf")
	public ResponseEntity<InputStreamResource> generarPdf(HttpSession session) throws IOException {
		List<UsuarioEntity> listaUsuarios = usuarioService.buscarUsuarios();

		String nombreUsuario = (String) session.getAttribute("usuario");
		if (nombreUsuario == null) {
			nombreUsuario = "Desconocido";
		}

		Map<String, Object> datosPdf = new HashMap<>();
		datosPdf.put("usuarios", listaUsuarios);
		datosPdf.put("nombreUsuario", nombreUsuario);

		ByteArrayInputStream pdfBytes = pdfService.generarPdf("usuarios_pdf", datosPdf);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=usuarios.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(pdfBytes));
	}

}
