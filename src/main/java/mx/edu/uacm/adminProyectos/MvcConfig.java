package mx.edu.uacm.adminProyectos;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	
	
	public void addViewControllers(ViewControllerRegistry registry){
		
		 registry.addViewController("/").setViewName("index");
		 registry.addViewController("/login").setViewName("login");
		 registry.addViewController("/registro").setViewName("registro");
		 registry.addViewController("/home").setViewName("home");
		 registry.addViewController("/ejercicios").setViewName("ejercicios");
		 registry.addViewController("/alimentos").setViewName("alimentos");
		 registry.addViewController("/padecimientos").setViewName("padecimientos");
		 registry.addViewController("/rutinas").setViewName("rutinas");
	}

}
