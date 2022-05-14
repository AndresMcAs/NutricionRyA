package mx.edu.uacm.adminProyectos.service;

import java.util.List;

import mx.edu.uacm.adminProyectos.AplicacionExcepcion;
import mx.edu.uacm.adminProyectos.dominio.Usuario;



public interface UsuarioService {
	  
	  /**
	  * Metodo para obtener al usuario 
	  * @param correo
	  * @param contrasenia
	  * @return Objeto usuario
	  */
	  Usuario obtenerUsuarioPorCorreoYContrasenia(String correo, String contrasenia);
		
	  Usuario registrarUsuario(Usuario usuario) throws AplicacionExcepcion;

	  List<Usuario> obtenerUsuarios();
	}