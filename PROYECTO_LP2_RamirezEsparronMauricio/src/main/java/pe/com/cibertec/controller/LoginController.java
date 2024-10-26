package pe.com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import pe.com.cibertec.model.entity.UsuarioEntity;
import pe.com.cibertec.service.UsuarioService;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final UsuarioService usuarioService;

	@GetMapping("/login")
	public String mostrarLogin(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("errorMessage", "Usuario o contraseña incorrectos.");
		}
		model.addAttribute("usuario", new UsuarioEntity());
		return "login";
	}

	@GetMapping("/menu")
	public String mostrarMenu(Model model, HttpSession session) {
		String usuarioNombre = (String) session.getAttribute("usuario");
		model.addAttribute("usuario", usuarioNombre);
		return "menu";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("usuario") UsuarioEntity usuarioFormulario, Model model, HttpSession session) {

		boolean usuarioValidado = usuarioService.validarUsuario(usuarioFormulario);
		if (usuarioValidado) {
			session.setAttribute("usuario", usuarioFormulario.getCorreo());
			return "redirect:/menu";
		}

		model.addAttribute("loginInvalido", "No existe el usuario");
		model.addAttribute("usuario", new UsuarioEntity());
		return "login";
	}

	@GetMapping("/logout")
	public String logout() {

		return "redirect:/home";
	}
}
