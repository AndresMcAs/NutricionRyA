package mx.edu.uacm.adminProyectos.dominio;


import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.NutricionRyAApplication;
import mx.edu.uacm.adminProyectos.repository.ComidaRepository;
import mx.edu.uacm.adminProyectos.service.imp.ComidaServiceImpl;

@SpringBootTest(classes = {NutricionRyAApplication.class})
@Slf4j
public class ComidaTest {
	
	@Autowired
	private ComidaRepository comidaRepo;
	
	@Disabled
	@Test
	public void crearComida() {
		log.debug("entrando a crearcomida()");
		Comida comida = new Comida("toma", "1", "234");
		comidaRepo.save(comida);
		
	}
	
	@Test
	public void busquedaProducto() {
		log.debug("entrando a buscar comida");
		Comida comida =comidaRepo.findByNombre("Col");
		System.out.println(comida.getNombre());
		//assertNotNull(comida);
		
	}
	
	

}
