package mx.edu.uacm.adminProyectos.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Ejercicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String estado;
	private String ejercicioAerobico;
	private String ejerccioFuerza;
	private String ejercicioFlexibilidad;
	
	public Ejercicio(String estado,String ejercicioAerobico, String ejerccioFuerza, String ejercicioFlexibilidad) {
		super();
		this.estado= estado;
		this.ejercicioAerobico = ejercicioAerobico;
		this.ejerccioFuerza = ejerccioFuerza;
		this.ejercicioFlexibilidad = ejercicioFlexibilidad;
	}
   
	
 
}
