package mx.edu.uacm.adminProyectos.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Comida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String porcion;
	private String calorias;
	
	public Comida(String nombre, String porcion, String calorias) {
		super();
		this.nombre = nombre;
		this.porcion = porcion;
		this.calorias = calorias;
	}

}
