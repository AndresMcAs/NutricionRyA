package mx.edu.uacm.adminProyectos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mx.edu.uacm.adminProyectos.dominio.Ejercicio;

public interface EjercicioRepository extends CrudRepository<Ejercicio, Long> {
	
	Ejercicio findByEstado(String estado);

	//@Query("SELECT u FROM ejercicio u WHERE u.estado = :estado")
	//public Ejercicio getEjercicioEstado(@Param("estado") String estado);
	
	
	public Long countById(Long id);

	
}

