package mx.edu.uacm.adminProyectos.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.dominio.Ejercicio;
import mx.edu.uacm.adminProyectos.repository.EjercicioRepository;
import mx.edu.uacm.adminProyectos.service.EjercicioService;

@Service
@Slf4j
public class EjercicioServiceImpl implements EjercicioService {
	
	@Autowired
	private EjercicioRepository ejercicioRepository;
	 	
	public Ejercicio obtenerEjercicioEstado(String estado) {
		
		if(log.isDebugEnabled())
			log.debug("> Entrando a UsuarioServiceImpl.obtenerUsuarioPorCorreoYContrasenia()");
		
		Ejercicio ejercicioRecuperado = ejercicioRepository.findByEstado(estado);
		
		log.debug("usuario recuperado: " + ejercicioRecuperado);
		
		if(ejercicioRecuperado != null && ejercicioRecuperado.getEstado().equals(estado)) {
			
			return ejercicioRecuperado;
		} else {
			
			return null;
		}

	}
	
}
