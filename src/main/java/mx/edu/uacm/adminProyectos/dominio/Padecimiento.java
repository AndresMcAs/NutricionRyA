package mx.edu.uacm.adminProyectos.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Padecimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String estado;
	private String padecimientos;
	private String tratamiento;
	
	public Padecimiento() {}
	public Padecimiento(String estado, String padecimientos, String tratamiento) {
		super();
		this.estado = estado;
		this.padecimientos = padecimientos;
		this.tratamiento = tratamiento;
	}
	
	
   
	
 
}
