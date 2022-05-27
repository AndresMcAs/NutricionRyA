package mx.edu.uacm.adminProyectos.controller;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.dominio.Ejercicio;
import mx.edu.uacm.adminProyectos.dominio.Usuario;
import mx.edu.uacm.adminProyectos.service.EjercicioService;
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
@RequestMapping("/ejercicio")
@Slf4j
public class EjercicioController {
	
  @Autowired
  private EjercicioService ejercicioService;
  private UsuarioController usarioc;
  /**
   * busca ejercicios
   * @param model
   * @return
   */
  @GetMapping("/buscarejercicio")
  public String buscar(Model model ,Ejercicio ejercicio) {
	  //obtenemos el usuario que inicio sesion de usuarioController
	  // le pasamos su estado
	  Usuario usuarioSesion = usarioc.getUsuario();
	if (log.isDebugEnabled())
      log.debug("> Entrando a buscarEjercicio <");
	 if (usuarioSesion.getEstado()!= null) {
      ejercicio = ejercicioService.obtenerEjercicioEstado(usuarioSesion.getEstado());
      model.addAttribute("ejercicio",ejercicio);
	 } else {
		 model.addAttribute("mensajeError", null);// faltaria poner una notificacion que perimero debe  calcular su imc
	 }
    return "ejercicio";
  }
  


}
