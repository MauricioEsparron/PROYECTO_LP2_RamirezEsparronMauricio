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

import pe.com.cibertec.model.entity.OperacionEntity;
import pe.com.cibertec.service.OperacionService;

@Controller
@RequestMapping("/operaciones") 
public class OperacionController {

    @Autowired
    private OperacionService operacionService;
    
    @GetMapping("/")
    public String listarOperaciones(Model model) {
        List<OperacionEntity> listaOperaciones = operacionService.buscarOperaciones();
        model.addAttribute("lista_operaciones", listaOperaciones);
        return "listar_operaciones";
    }
    
    @GetMapping("/registrar_operacion")
    public String mostrarRegistrarOperacion(Model model) {
        model.addAttribute("operacion", new OperacionEntity());
        return "registrar_operacion";
    }
    
    @PostMapping("/registrar_operacion")
    public String registrarOperacion(@ModelAttribute("operacion") OperacionEntity operacion, Model model) {
        operacionService.crearOperacion(operacion);
        return "redirect:/operaciones/";
    }
    
    @GetMapping("/detalle_operacion/{id}")
    public String verDetalleOperacion(@PathVariable("id") Integer id, Model model) {
        OperacionEntity operacion = operacionService.buscarOperacionPorId(id);
        model.addAttribute("operacion", operacion);
        return "detalle_operacion";
    }
    
    @GetMapping("/delete_operacion/{id}")
    public String deleteOperacion(@PathVariable("id") Integer id) {
        operacionService.eliminarOperacion(id);
        return "redirect:/operaciones/";
    }
    
    @GetMapping("/editar_operacion/{id}")
    public String mostrarActualizarOperacion(@PathVariable("id") Integer id, Model model) {
        OperacionEntity operacion = operacionService.buscarOperacionPorId(id);
        model.addAttribute("operacion", operacion);
        return "editar_operacion";
    }
    
    @PostMapping("/editar_operacion/{id}")
    public String actualizarOperacion(@PathVariable("id") Integer id, @ModelAttribute("operacion") OperacionEntity operacion) {
        operacionService.actualizarOperacion(id, operacion);
        return "redirect:/operaciones/";
    }
}
