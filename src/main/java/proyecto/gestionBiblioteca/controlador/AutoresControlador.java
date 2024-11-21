package proyecto.gestionBiblioteca.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.gestionBiblioteca.modelo.Autores;
import proyecto.gestionBiblioteca.servicio.IAutoresServicio;

import java.util.List;

@Controller
@RequestMapping(value = "/autor")
public class AutoresControlador {

    @Autowired
    private IAutoresServicio autoresServicio;
    private List<Autores> listaResultado;

    //Listar
    @GetMapping("/lista")
    public String listarAutores(Model model) {
        listaResultado = autoresServicio.listarAutores();
        System.out.println(listaResultado);
        model.addAttribute("Lista", listaResultado);
        return "listarAutores";
    }

    //Crear
    @GetMapping("/nuevoAutor")
    public String nuevoAutor(Model model) {
        model.addAttribute("nuevoAutor", new Autores());
        return "nuevoAutor";
    }

    //Guardar
    @PostMapping("/guardarAutor")
    public String guardarAutor(@ModelAttribute("nuevoAutor") Autores nuevoAutor) {
        autoresServicio.insertarAutores(nuevoAutor);
        return "redirect:/autor/lista";
    }

    //Actualizar
    @GetMapping("/editarAutor/{id}")
    public String actualizarAutor(Model model, @PathVariable(value="id") int id) {
        model.addAttribute("nuevoAutor", autoresServicio.bucarId(id));
        return "nuevoAutor";
    }

    //Eliminar
    @GetMapping("/eliminarAutor/{id}")
    public String eliminarAutor(@PathVariable(value = "id") int id) {
        autoresServicio.eliminarAutores(id);
        return "redirect:/autor/lista";
    }
}
