package mx.edu.uacm.adminProyectos.service.imp;

import java.util.ArrayList;
import java.util.List;

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
	private List <Ejercicio> listaEjercicios;
	
	@Override
	public Ejercicio obtenerEjercicioEstado(String estado) {
		
		if(log.isDebugEnabled())
			log.debug("> entrando a EjercicioService.obtenerEjercicio");
		 Ejercicio ejercicioEstado =ejercicioRepository.findByEstado(estado);
		if (ejercicioEstado != null)
		 return ejercicioEstado;
		else 
			return null;
	}
    /**
     * lista todos lo ejercicio registrados
     */
	@Override
	public List<Ejercicio> obtenerEjercicios() {
		listaEjercicios = new ArrayList<>();
		listaEjercicios =(List<Ejercicio>) ejercicioRepository.findAll();
		return listaEjercicios;
	}
		

}
