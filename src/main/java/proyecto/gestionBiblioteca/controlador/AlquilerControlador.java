package proyecto.gestionBiblioteca.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import proyecto.gestionBiblioteca.modelo.Alquiler;
import proyecto.gestionBiblioteca.servicio.IAlquilerServicio;

import java.util.List;

@Controller
public class AlquilerControlador {

    @Autowired
    private IAlquilerServicio servicioAlquiler;
    private List<Alquiler> listaResultado;

    //Listar
    @GetMapping("/alquiler")
    public String listarAlquiler(Model model) {
        listaResultado = servicioAlquiler.listarAlquiler();
        System.out.println(listaResultado);
        model.addAttribute("Lista", listaResultado);
        return "listarAlquiler";
    }

    // Nuevo
    @GetMapping("/nuevoAlquiler")
    public String nuevoAlquiler(Model model) {
        model.addAttribute("nuevoAlquiler", new Alquiler());
        return "nuevoAlquiler";
    }

    //Guardar
    @PostMapping("/guardarAlquiler")
    public String guardarAlquiler(@ModelAttribute("nuevoAlquiler") Alquiler nuevoAlquiler) {
        servicioAlquiler.insertarAlquiler(nuevoAlquiler);
        return "redirect:/alquiler";
    }

    //Editar
    @GetMapping("/editarAlquiler/{id}")
    public String actualizarAlquiler(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("nuevoAlquiler", servicioAlquiler.buscarId(id));
        return "nuevoAlquiler";
    }

    //Eliminar
    @GetMapping("/eliminarAlquiler/{id}")
    public String eliminarAlquiler(@PathVariable(value = "id") int id) {
        servicioAlquiler.eliminarAlquiler(id);
        return "redirect:/alquiler";
    }
}
