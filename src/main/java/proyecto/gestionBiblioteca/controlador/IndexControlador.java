package proyecto.gestionBiblioteca.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlador {
	
	@GetMapping(value = "/")
    public String index() {
        return "Plantilla/Plantilla";
    }

}
