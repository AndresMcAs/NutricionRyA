package mx.edu.uacm.adminProyectos.controller;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.dominio.Ejercicio;
import mx.edu.uacm.adminProyectos.dominio.Rutina;
import mx.edu.uacm.adminProyectos.dominio.Usuario;
import mx.edu.uacm.adminProyectos.service.EjercicioService;
import mx.edu.uacm.adminProyectos.service.RutinaEjercicioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controlador para la busqueda de ejericios
 * @author Andres Mendoza 
 *
 */
@Controller
@Slf4j
public class RutinaEjercicioController {
	
  @Autowired
  private RutinaEjercicioService rutinaEjercicioService;

  
  @GetMapping("/rutinas")
  public String buscar(Model model ,Rutina rutina) {
	 
	if (log.isDebugEnabled())
      log.debug("> Entrando a buscarEjercicio <");
	List<Rutina> listaRutinas = rutinaEjercicioService.obtenerEjercicios();
	model.addAttribute("listaRutinas", listaRutinas);
    return "rutinas";
  }
  


}
