package proyecto.gestionBiblioteca.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String password;
	private String direccion;
	@Builder.Default
	private Date fechaRegistro = new Date();
	@Builder.Default
	private Boolean activo = Boolean.FALSE;
	
	@ManyToOne
	@JoinColumn(name = "fkRoles")
	private Roles fkRoles;
}
