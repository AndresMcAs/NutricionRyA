package mx.edu.uacm.adminProyectos.repository;

import org.springframework.data.repository.CrudRepository;

import mx.edu.uacm.adminProyectos.dominio.Padecimiento;


public interface PadecimientoRepository extends CrudRepository<Padecimiento, Long> {
	
	Padecimiento findByEstado(String estado);

	
}

