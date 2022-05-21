package mx.edu.uacm.adminProyectos.dominio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.NutricionRyAApplication;
import mx.edu.uacm.adminProyectos.repository.EjercicioRepository;

@SpringBootTest(classes = {NutricionRyAApplication.class})
@Slf4j
public class EjercicioTest {
	
	@Autowired
	private EjercicioRepository ej;
	
	@Test
	public void crearEjercicio() {
		
		Ejercicio e = new Ejercicio("Peso normal","correr", "pesa", "Yoga");
		
		ej.save(e);
	}

}
