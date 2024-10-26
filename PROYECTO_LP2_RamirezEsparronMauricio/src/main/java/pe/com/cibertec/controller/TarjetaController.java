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

import pe.com.cibertec.model.entity.TarjetaEntity;
import pe.com.cibertec.model.entity.TipoTarjetaEntity;
import pe.com.cibertec.service.TarjetaService;
import pe.com.cibertec.service.TipoTarjetaService;

@Controller
@RequestMapping("/tarjetas")
public class TarjetaController {

	@Autowired
	private TarjetaService tarjetaService;

	@Autowired
	private TipoTarjetaService tipoTarjetaService;

	@GetMapping("/")
	public String listarTarjetas(Model model) {
		List<TarjetaEntity> listaTarjetas = tarjetaService.buscarTarjetas();
		model.addAttribute("lista_tarjetas", listaTarjetas);
		List<TipoTarjetaEntity> listaTipoTarjetas = tipoTarjetaService.buscarTiposTarjeta();
		model.addAttribute("lista_tipoTarjetas", listaTipoTarjetas);
		return "listar_tarjetas";
	}

	@GetMapping("/registrar_tarjeta")
	public String mostrarRegistrarTarjeta(Model model) {
		model.addAttribute("tarjeta", new TarjetaEntity());
		return "registrar_tarjeta";
	}

	@PostMapping("/registrar_tarjeta")
	public String registrarTarjeta(@ModelAttribute("tarjeta") TarjetaEntity tarjeta, Model model) {
		tarjetaService.crearTarjeta(tarjeta);
		return "redirect:/tarjetas/";
	}

	@GetMapping("/detalle_tarjeta/{id}")
	public String verDetalleTarjeta(@PathVariable("id") Integer id, Model model) {
		TarjetaEntity tarjeta = tarjetaService.buscarTarjetaPorId(id);
		model.addAttribute("tarjeta", tarjeta);
		return "detalle_tarjeta";
	}

	@GetMapping("/delete_tarjeta/{id}")
	public String deleteTarjeta(@PathVariable("id") Integer id) {
		tarjetaService.eliminarTarjeta(id);
		return "redirect:/tarjetas/";
	}

	@GetMapping("/editar_tarjeta/{id}")
	public String mostrarActualizarTarjeta(@PathVariable("id") Integer id, Model model) {
		TarjetaEntity tarjeta = tarjetaService.buscarTarjetaPorId(id);
		model.addAttribute("tarjeta", tarjeta);
		return "editar_tarjeta";
	}

	@PostMapping("/editar_tarjeta/{id}")
	public String actualizarTarjeta(@PathVariable("id") Integer id, @ModelAttribute("tarjeta") TarjetaEntity tarjeta) {
		tarjetaService.actualizarTarjeta(id, tarjeta);
		return "redirect:/tarjetas/";
	}
}
