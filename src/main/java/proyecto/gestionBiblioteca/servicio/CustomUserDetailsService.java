package proyecto.gestionBiblioteca.servicio;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import proyecto.gestionBiblioteca.modelo.CustomUserDetails;
import proyecto.gestionBiblioteca.modelo.Usuario;
import proyecto.gestionBiblioteca.repositorio.IUsuarioRepositorio;


@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final IUsuarioRepositorio repositorioUsuario;;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = repositorioUsuario.findFirstByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User does not exist in DB");
        }
        return CustomUserDetails.builder().usuario(user).build();
    }
}
