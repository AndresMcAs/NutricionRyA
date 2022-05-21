package mx.edu.uacm.adminProyectos.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.AplicacionExcepcion;
import mx.edu.uacm.adminProyectos.dominio.Usuario;
import mx.edu.uacm.adminProyectos.repository.UsuarioRepository;
import mx.edu.uacm.adminProyectos.service.UsuarioService;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;   
    private Usuario usuarioM;
   
	/**
	 * {@link mx.edu.uacm.progweb.evaluacionanonima.service.UsuarioService#obtenerUsuarioPorCorreoYContrasenia(String, String)}
	 */
	public Usuario obtenerUsuarioPorCorreoYContrasenia(String correo, String contraseniaPlana) {
		
		if(log.isDebugEnabled())
			log.debug("> Entrando a UsuarioServiceImpl.obtenerUsuarioPorCorreoYContrasenia()");
		
		Usuario usuarioRecuperado = usuarioRepository.findByCorreo(correo);
		
		log.debug("usuario recuperado: " + usuarioRecuperado);
		
		if(usuarioRecuperado != null && passwordEncoder.matches(contraseniaPlana, usuarioRecuperado.getContrasenia())) {
			
			return usuarioRecuperado;
		} else {
			
			return null;
		}

	}
	
	@Override
	public Usuario registrarUsuario(Usuario usuario) throws AplicacionExcepcion {
		if (log.isDebugEnabled())
			log.debug(" > Entrando a UsuarioService.registrarUsuario");
		
		 Usuario usuarioGuardado = null;
		 
		 try {
			
			usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia())); 
			usuarioGuardado = usuarioRepository.save(usuario);
		
		 } catch (DataAccessException e) {
			 log.error(e.getMessage());
             throw new AplicacionExcepcion("Error al guardar el registro");		
		 
		 }
		 
		return usuarioGuardado;
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		
		return (List<Usuario>)usuarioRepository.findAll();
	}

	@Override
	public double calcularIMC(Usuario usuario) throws AplicacionExcepcion {
		// TODO Auto-generated method stub
		return usuario.CalculoIMC();
	}
  
	 @Override
	  public Usuario modificarUsuario(Usuario usuario) throws Exception {
		  
	    Usuario usuarioGuardar = buscarUsuario(usuario.getId());
	    modificar(usuario,usuarioGuardar);
	    return usuarioRepository.save(usuarioGuardar);
		
	  }
	  
	  private  void modificar(Usuario usuario1 , Usuario usuario2) throws AplicacionExcepcion {
		  usuario2.setNombre(usuario2.getNombre());
		  usuario2.setApellidoPat(usuario2.getApellidoPat());
		  usuario2.setApellidoMat(usuario2.getApellidoMat());
		  usuario2.setCorreo(usuario2.getCorreo());
		  usuario2.setContrasenia(usuario2.getContrasenia());
		  //modificacion de datos
		  usuario2.setPeso(usuario1.getPeso());
		  usuario2.setTalla(usuario1.getTalla());
		  usuario2.setImc(usuario1.getImc());
		  usuario2.setEstado(usuario1.getEstado());
	  }

	@Override
	public Usuario buscarUsuario(Long id) throws Exception {
		try {	
		      usuarioM = usuarioRepository.findById(id).get(); 
		    } catch (DataAccessException e) {
		      throw new AplicacionExcepcion("Error");
		    }
		    return usuarioM;
	}
	 
	

}
