package mx.edu.uacm.adminProyectos;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer{
	
	
	public void addViewControllers(ViewControllerRegistry registry){
		
		 registry.addViewController("/").setViewName("index");
		 registry.addViewController("/login").setViewName("login");
	}

}
