package mx.edu.uacm.adminProyectos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mx.edu.uacm.adminProyectos.dominio.Comida;


public interface ComidaService {
	  
	
	  Comida obtenerComidaNombre(String nombre);
	  Page<Comida> obtenerPlatillosPaginados(Pageable pageable);
	  List<Comida> listAll ();
	 
}