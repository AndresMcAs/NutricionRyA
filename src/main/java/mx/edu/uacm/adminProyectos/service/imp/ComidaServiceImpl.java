package mx.edu.uacm.adminProyectos.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.dominio.Comida;
import mx.edu.uacm.adminProyectos.repository.ComidaRepository;
import mx.edu.uacm.adminProyectos.service.ComidaService;


@Service
@Slf4j
public class ComidaServiceImpl implements ComidaService {
	
	@Autowired
	private ComidaRepository comidaRepository;
	
	public Comida obtenerComidaNombre(String nombre) {
		
		if(log.isDebugEnabled())
			log.debug("> entrando a EjercicioService.obtenerEjercicio");
		  
		Comida comidaRecuperada = comidaRepository.findByNombre(nombre);
		
		log.debug("usuario recuperado: " + comidaRecuperada);
		
		if(comidaRecuperada != null) {
			
			return comidaRecuperada;
		} else {
			
			return null;
		}

	}
	
	@Override
	public Page<Comida> obtenerPlatillosPaginados(Pageable pageable) {
		
		return comidaRepository.findAll(pageable);
	}
	
	@Override
	public List<Comida> listAll (){
		return (List<Comida>) comidaRepository.findAll();
	}
	

}
