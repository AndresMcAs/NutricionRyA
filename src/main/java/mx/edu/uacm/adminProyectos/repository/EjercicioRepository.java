package mx.edu.uacm.adminProyectos.repository;

import org.springframework.data.repository.CrudRepository;
import mx.edu.uacm.adminProyectos.dominio.Ejercicio;

public interface EjercicioRepository extends CrudRepository<Ejercicio, Long> {
	
	Ejercicio findByEstado(String estado);

	
}

