package mx.edu.uacm.adminProyectos.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.AplicacionExcepcion;
import mx.edu.uacm.adminProyectos.dominio.Comida;
import mx.edu.uacm.adminProyectos.dominio.Ejercicio;
import mx.edu.uacm.adminProyectos.dominio.Padecimiento;
import mx.edu.uacm.adminProyectos.dominio.Usuario;
import mx.edu.uacm.adminProyectos.service.ComidaService;
import mx.edu.uacm.adminProyectos.service.EjercicioService;
import mx.edu.uacm.adminProyectos.service.PadecimientoService;
import mx.edu.uacm.adminProyectos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * controlador_de_usuarios
 * 
 * @author Andres_Mendoza
 *
 */
@Controller
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private PadecimientoService padeciemientoService;
	@Autowired
	private EjercicioService ejercicioService;
	private String registrar;
	private String paginaInicio;
	private ServletContext servletContext;
	private Usuario usuarioSesion;

	public UsuarioController(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * logueo_de_usuarios
	 * 
	 * @param correo
	 * @param password
	 * @param model
	 * @return
	 */
	@PostMapping("/login")
	public String iniciarSesion(@RequestParam("correo") String correo, @RequestParam("contrasenia") String password,
			Model model) {

		if (log.isDebugEnabled())
			log.debug("> Entrando al metodo iniciarSesion <");
		Usuario usuario = usuarioService.obtenerUsuarioPorCorreoYContrasenia(correo, password);

		if (usuario != null) {
			httpSession.setAttribute("usuarioLogueado", usuario);
			usuarioSesion = usuario;
			model.addAttribute("usuarioSesion", usuario);
			paginaInicio = "home";

		} else {
			servletContext.setAttribute("errorMensaje", "Usuario/Contrasenia no validos");
			paginaInicio = "redirect:/login";

		}
		return paginaInicio;
	}

	@GetMapping("/logout")
	public String logout() {
		httpSession.removeAttribute("usuarioLogueado");
		return "redirect:/";
	}

	@GetMapping("/initlogin")
	public String iniciarLogin() {
		servletContext.removeAttribute("errorMensaje");
		return "redirect:/login";
	}

	/**
	 * registro_de_usuarios
	 * 
	 * @param model
	 * @param usuario
	 * @return
	 */
	@GetMapping("/registro")
	public String registrarUsuario(Model model, Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug(">Entrando a usuarioController.registrarusuario");
			log.debug("Usuario {}", usuario);
		}

		if (usuario.getNombre() != null && usuario.getCorreo() != null) {

			try {
				Usuario usuarioGuardado;
				usuarioGuardado = usuarioService.registrarUsuario(usuario);
				if (usuarioGuardado != null && usuarioGuardado.getId() != null)
					model.addAttribute("mensajeExitoso", "Registro exitoso!" + usuario.getNombre());
			} catch (AplicacionExcepcion e) {
				log.error(e.getMessage());
				model.addAttribute("mensajeError", e.getMessage());
			}
			registrar = "registro::#modalMensaje";
		} else {
			registrar = "redirect:/registro";
		}
		return registrar;
	}

	/**
	 * 
	 * @param model
	 * @param usuario
	 * @return
	 * @throws AplicacionExcepcion
	 */
	@GetMapping("/mostrarusuario")
	public String mostrarUsuario(Model model) throws AplicacionExcepcion {
		if (log.isDebugEnabled()) {
			log.debug("> Entrando a usuariocontroller.buscaUsuario");

		}
		model.addAttribute("usuarioSesion", usuarioSesion);
		return "home";
	}

	/*
	 * funcion_que_calcula_el_IMC_del_Usuario
	 * 
	 * @param model
	 * @param usuario
	 * @param talla
	 * @param peso
	 * @return
	 */
	@GetMapping("/calculo")
	public String calculoImc(Model model, Usuario usuario, double talla, double peso) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug(">Entrando a usuarioController calculo imc");
			log.debug("Usuario {}", usuario);
		}

		try {
			usuario = usuarioSesion;
			usuario.setPeso(peso);
			usuario.setTalla(talla);
			double imcC = Double.parseDouble(String.format("%.2f", usuarioService.calcularIMC(usuario)));
			usuario.setImc(imcC);
			usuario.getImc();

			if (usuario.getImc() < 18.5) {
				
				model.addAttribute("mensajeExitoso", usuario.getNombre() + " " + usuario.getApellidoPat()
						+ "\n tu IMC es de: " + String.format("%.2f", usuario.getImc()));
				usuario.setEstado("Bajo Peso");
			}

			else if (usuario.getImc() < 24.9) {
				
				model.addAttribute("mensajeExitoso", usuario.getNombre() + " " + usuario.getApellidoPat()
						+ "\n tu IMC es de: " + String.format("%.2f", usuario.getImc()));
				usuario.setEstado("Peso Normal");
			}

			else if (usuario.getImc() < 29.9) {
				
				model.addAttribute("mensajeExitoso", usuario.getNombre() + " " + usuario.getApellidoPat()
						+ "\n tu IMC es de: " + String.format("%.2f", usuario.getImc()));
				usuario.setEstado("Sobrepeso");
			} else if (usuario.getImc() < 34.9) {
				
				model.addAttribute("mensajeExitoso", usuario.getNombre() + " " + usuario.getApellidoPat()
						+ "\n tu IMC es de: " + String.format("%.2f", usuario.getImc()));
				usuario.setEstado("Obesidad Grado 1");
			} else if (usuario.getImc() < 39.9) {
				
				model.addAttribute("mensajeExitoso", usuario.getNombre() + " " + usuario.getApellidoPat()
						+ "\n tu IMC es de: " + String.format("%.2f", usuario.getImc()));
				usuario.setEstado("Obesidad Grado 2");
			} else if (usuario.getImc() >= 40) {
				
				model.addAttribute("mensajeExitoso", usuario.getNombre() + " " + usuario.getApellidoPat()
						+ "\n tu IMC es de: " + String.format("%.2f", usuario.getImc()));
				usuario.setEstado("Obesidad Grado 3");
			}
			
			usuarioService.modificarUsuario(usuario);
		} catch (AplicacionExcepcion e) {
			log.error(e.getMessage());
			model.addAttribute("mensajeError", e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
           
			model.addAttribute("usuarioSesion", usuario);
		}

		return "home::#modalMensaje";
	}

	public Usuario getUsuario() {
		return usuarioSesion;
	}
	
	 /**
	   * busca ejercicios
	   * @param model
	   * @return
	   */
	  @GetMapping("/buscarejercicio")
	  public String buscar(Model model ,Ejercicio ejercicio) {
		 String pagina= "ejercicios";
		if (log.isDebugEnabled())
	      log.debug("> Entrando a buscarEjercicio <");
		 if (usuarioSesion.getEstado()!= null) {
	      ejercicio = ejercicioService.obtenerEjercicioEstado(usuarioSesion.getEstado());
	      model.addAttribute("ejercicio",ejercicio);
		 } else {
			 model.addAttribute("mensajeError", "Calcula tu imc antes de buscar ejercicios");
		    pagina="home::#modalMensaje";
		 }
	    return pagina;
	  }
	  
	  @GetMapping("/checate")
	  public String buscarPadecimiento(Model model ,Padecimiento padecimiento) {
		 String pagina= "padecimientos";
		if (log.isDebugEnabled())
	      log.debug("> Entrando a buscarEjercicio <");
		 if (usuarioSesion.getEstado()!= null) {
	      padecimiento = padeciemientoService.obtenerPadecimientoEstado(usuarioSesion.getEstado());
	      model.addAttribute("padecimiento",padecimiento);
		 } else {
			 model.addAttribute("mensajeError", "Calcula tu imc antes de buscar padecimientos");
		    pagina="home::#modalMensaje";
		 }
	    return pagina;
	  }
	  
}
