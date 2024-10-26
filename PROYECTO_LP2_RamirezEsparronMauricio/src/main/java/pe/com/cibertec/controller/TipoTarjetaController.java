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

import pe.com.cibertec.model.entity.TipoTarjetaEntity;
import pe.com.cibertec.service.TipoTarjetaService;

@Controller
@RequestMapping("/tipotarjetas")
public class TipoTarjetaController {

    @Autowired
    private TipoTarjetaService tipoTarjetaService;
    
    @GetMapping("/")
    public String listarTipoTarjetas(Model model) {
        List<TipoTarjetaEntity> listaTipoTarjetas = tipoTarjetaService.buscarTiposTarjeta();
        model.addAttribute("lista_tipotarjetas", listaTipoTarjetas);
        return "listar_tipotarjetas";
    }
    
    @GetMapping("/registrar_tipotarjeta")
    public String mostrarRegistrarTipoTarjeta(Model model) {
        model.addAttribute("tipotarjeta", new TipoTarjetaEntity());
        return "registrar_tipotarjeta";
    }
    
    @PostMapping("/registrar_tipotarjeta")
    public String registrarTipoTarjeta(@ModelAttribute("tipotarjeta") TipoTarjetaEntity tipoTarjeta, Model model) {
        tipoTarjetaService.crearTipoTarjeta(tipoTarjeta);
        return "redirect:/tipotarjetas/";
    }
    
    @GetMapping("/detalle_tipotarjeta/{id}")
    public String verDetalleTipoTarjeta(@PathVariable("id") Integer id, Model model) {
        TipoTarjetaEntity tipoTarjeta = tipoTarjetaService.buscarTipoTarjetaPorId(id);
        model.addAttribute("tipotarjeta", tipoTarjeta);
        return "detalle_tipotarjeta";
    }
    
    @GetMapping("/delete_tipotarjeta/{id}")
    public String deleteTipoTarjeta(@PathVariable("id") Integer id) {
        tipoTarjetaService.eliminarTipoTarjeta(id);
        return "redirect:/tipotarjetas/";
    }
    
    @GetMapping("/editar_tipotarjeta/{id}")
    public String mostrarActualizarTipoTarjeta(@PathVariable("id") Integer id, Model model) {
        TipoTarjetaEntity tipoTarjeta = tipoTarjetaService.buscarTipoTarjetaPorId(id);
        model.addAttribute("tipotarjeta", tipoTarjeta);
        return "editar_tipotarjeta";
    }
    
    @PostMapping("/editar_tipotarjeta/{id}")
    public String actualizarTipoTarjeta(@PathVariable("id") Integer id, @ModelAttribute("tipotarjeta") TipoTarjetaEntity tipoTarjeta) {
        tipoTarjetaService.actualizarTipoTarjeta(id, tipoTarjeta);
        return "redirect:/tipotarjetas/";
    }
}
