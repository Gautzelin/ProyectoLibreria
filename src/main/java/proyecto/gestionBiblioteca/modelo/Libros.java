package proyecto.gestionBiblioteca.modelo;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Libros implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tituloLibro;
	private String generoLibro;
	private Year anioPublicacion;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "libros_autores",
			joinColumns = @JoinColumn(name= "fkLibros", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "fkAutor", referencedColumnName = "id")
	)
	private List<Autores> autores = new ArrayList<>();

}
