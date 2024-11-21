package proyecto.gestionBiblioteca.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.gestionBiblioteca.modelo.Ejemplar;
import proyecto.gestionBiblioteca.servicio.IEjemplarServicio;

import java.util.List;

@Controller
@RequestMapping(value = "/ejemplar")
public class EjemplarControlador {

    @Autowired
    private IEjemplarServicio servicioEjemplar;
    private List<Ejemplar> listaResultado;

    @GetMapping("/lista")
    public String listarEjemplar(Model model){
        listaResultado = servicioEjemplar.listarEjemplar();
        model.addAttribute("Lista", listaResultado);
        return "listarEjemplar";
    }

    @GetMapping("/nuevoEjemplar")
    public String nuevoEjemplar(Model model){
        model.addAttribute("nuevoEjemplar", new Ejemplar());
        return "nuevoEjemplar";
    }

    @PostMapping("/guardarEjemplar")
    public String guardarEjemplar(@ModelAttribute("nuevo") Ejemplar nuevoEjemplar) {
        servicioEjemplar.insertarEjemplar(nuevoEjemplar);
        return "redirect:/ejemplar/lista";
    }

    @GetMapping("/editarEjemplar/{id}")
    public String actualizarEjemplar(Model model, @PathVariable(value="id") int id) {
        model.addAttribute("nuevoEjemplar", servicioEjemplar.buscarId(id));
        return "nuevoEjemplar";
    }

    @GetMapping("/eliminarEjemplar/{id}")
    public String eliminarEjemplar(@PathVariable(value="id") int id) {
        servicioEjemplar.eliminarEjemplar(id);
        return "redirect:/ejemplar/lista";
    }
}
