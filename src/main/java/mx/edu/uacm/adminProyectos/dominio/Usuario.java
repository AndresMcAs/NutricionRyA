package mx.edu.uacm.adminProyectos.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import mx.edu.uacm.adminProyectos.AplicacionExcepcion;


/**
 * 
 * @author Andres_Mendoza 
 *
 */
@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellidoPat;
	private String apellidoMat;
	private String correo;
	private String contrasenia;
	private double peso;
	private double talla;
	private double imc;
	private String estado;

	public Usuario() {

	}

	public void setPeso(double peso) throws AplicacionExcepcion {
		if (peso <= 0)
			throw new AplicacionExcepcion("ingresa una cantidada positiva ");
		else
			this.peso = peso;
	}
	public void setTalla(double talla) throws AplicacionExcepcion {
		
		if (talla <=0)
			throw new  AplicacionExcepcion("ingresa una talla positiva");
		else 
			this.talla = talla;
	}

	public double CalculoIMC() {
        double c = this.peso / Math.pow(this.talla, 2);
        this.imc = c;
		return  c;
	}
}
