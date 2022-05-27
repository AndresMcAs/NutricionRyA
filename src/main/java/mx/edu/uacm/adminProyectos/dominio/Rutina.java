package mx.edu.uacm.adminProyectos.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Rutina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ejercicio;
	private String duracion;
	private String series;
	private String repeticiones;
	private String descanso;
	
	public Rutina() {}

	public Rutina(String ejercicio, String duracion, String series, String repeticiones, String descanso) {
		super();
		this.ejercicio = ejercicio;
		this.duracion = duracion;
		this.series = series;
		this.repeticiones = repeticiones;
		this.descanso = descanso;
	}
	
   
	
 
}
