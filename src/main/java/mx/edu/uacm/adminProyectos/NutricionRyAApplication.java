package mx.edu.uacm.adminProyectos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class NutricionRyAApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutricionRyAApplication.class, args);
	}

}
