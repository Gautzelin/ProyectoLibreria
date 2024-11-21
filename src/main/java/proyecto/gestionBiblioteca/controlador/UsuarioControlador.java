package proyecto.gestionBiblioteca.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import proyecto.gestionBiblioteca.modelo.Usuario;
import proyecto.gestionBiblioteca.servicio.IRolesServicio;
import proyecto.gestionBiblioteca.servicio.IUsuarioServicio;

@Controller()
@RequestMapping(value = "/usuario")
public class UsuarioControlador {

	@Autowired
	private IUsuarioServicio servicioUsuario;
	private List<Usuario> listaResultado;
	@Autowired
	private IRolesServicio rolesServicio;

	// Listar
	@GetMapping("/lista")
	public String listarUsuario(Model model) {
		listaResultado = servicioUsuario.listarUsuario();
		System.out.println(listaResultado);
		model.addAttribute("Lista", listaResultado);
		return "listarUsuario";
	}

	// crear nuevo usuario registro
	@GetMapping("/nuevoUsuario")
	public String nuevoUsuario(Model model) {
		var roles = rolesServicio.listarRoles();
		System.out.println("Estos son los roles we....");
		System.out.println(roles);
		model.addAttribute("nuevoUsuario", new Usuario());
		model.addAttribute("roles", rolesServicio.listarRoles());
		return "nuevoUsuario";
	}

	// Guardar usuario
	@PostMapping("/guardarUsuario")
	public String guardarUsuario(@ModelAttribute("nuevo") Usuario nuevoUsuario) {
		servicioUsuario.insertarUsuario(nuevoUsuario);
		return "redirect:/usuario/lista";
	}

	// Actualizar usuario
	@GetMapping("/editar/{idUsuario}")
	public String actualizarUsuario(Model model, @PathVariable(value = "idUsuario") int idUsuario) {
		var roles = rolesServicio.listarRoles();
		System.out.println("Estos son los roles we....");
		System.out.println(roles);
		model.addAttribute("nuevoUsuario", servicioUsuario.buscarId(idUsuario));
		model.addAttribute("roles", rolesServicio.listarRoles());
		return "nuevoUsuario";
	}
	
	// Eliminar usuario
	@GetMapping("/eliminar/{idUsuario}")
	public String eliminarUsuario(@PathVariable(value = "idUsuario") int idUsuario) {
	    servicioUsuario.eliminarUsuario(idUsuario);
	    return "redirect:/usuario/lista";
	}
}
