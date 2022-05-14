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

}
