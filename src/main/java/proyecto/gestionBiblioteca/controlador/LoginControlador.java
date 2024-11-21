package proyecto.gestionBiblioteca.controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import proyecto.gestionBiblioteca.modelo.Usuario;
import proyecto.gestionBiblioteca.servicio.IUsuarioServicio;

@Controller
@RequiredArgsConstructor
public class LoginControlador {
    private final IUsuarioServicio servicioUsuario;
    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("credenciales", new Usuario()); // Objeto para enlazar datos del formulario
        return "login";
    }

    // crear nuevo usuario registro
    @GetMapping("/nuevoUsuario")
    public String nuevoUsuario(Model model) {
        model.addAttribute("nuevoUsuario", new Usuario());
        return "registrarUsuario";
    }

    // Guardar usuario
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute("nuevo") Usuario nuevoUsuario) {
        servicioUsuario.insertarUsuario(nuevoUsuario);
        return "redirect:/login";
    }
}
