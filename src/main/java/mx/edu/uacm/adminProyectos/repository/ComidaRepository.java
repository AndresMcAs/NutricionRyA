package mx.edu.uacm.adminProyectos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import mx.edu.uacm.adminProyectos.dominio.Comida;


public interface ComidaRepository extends CrudRepository<Comida, Long> {
	
	Comida findByNombre(String nombre);

	Page<Comida> findAll(Pageable pageable);

	

	
}

