package mx.edu.uacm.adminProyectos.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;


/**
 * 
 * @author Andres Mendoza 
 *
 */
@Entity
@Data
public class Usuario  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String apellidoPat;
  private String apellidoMat;
  private String correo;
  private String contrasenia; 
 

  public Usuario() {

  }
  
}
