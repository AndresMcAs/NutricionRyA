package mx.edu.uacm.adminProyectos.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.dominio.Rutina;
import mx.edu.uacm.adminProyectos.repository.RutinaEjercicioRepository;
import mx.edu.uacm.adminProyectos.service.RutinaEjercicioService;

@Service
@Slf4j
public class RutinaEjercicioServiceImpl implements RutinaEjercicioService {
	
	@Autowired
	private RutinaEjercicioRepository rutinaEjercicioRepository;
	private List <Rutina> listaEjercicios;
	

    /**
     * lista todos lo ejercicio registrados
     */
	@Override
	public List<Rutina> obtenerEjercicios() {
		listaEjercicios = new ArrayList<>();
		listaEjercicios =(List<Rutina>) rutinaEjercicioRepository.findAll();
		return listaEjercicios;
	}
		

}
