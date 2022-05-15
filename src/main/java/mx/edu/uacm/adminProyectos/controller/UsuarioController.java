package mx.edu.uacm.adminProyectos.controller;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.AplicacionExcepcion;
import mx.edu.uacm.adminProyectos.dominio.Usuario;
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
			paginaInicio = "redirect:/home";

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
	 * lista_de_usuarios
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/buscar")
	public String buscar(Model model) {
		if (log.isDebugEnabled())
			log.debug("entrando a buscar usuarios ");
		List<Usuario> usuarios = usuarioService.obtenerUsuarios();
		model.addAttribute("usuarios", usuarios);
		return "home-revision";
		// evaluacion

	}

	/*
	 * funcion_que_calcula_el_IMC_del_Usuario
	 * @param model
	 * @param usuario
	 * @param talla
	 * @param peso
	 * @return
	 */
	@GetMapping("/calculo")
	public String calculoImc(Model model, Usuario usuario, double talla, double peso) throws AplicacionExcepcion{
		if (log.isDebugEnabled()) {
			log.debug(">Entrando a usuarioController calculo imc");
			log.debug("Usuario {}", usuario);
		}

		try {
			usuario = usuarioSesion;
			usuario.setPeso(peso);
			usuario.setTalla(talla);
			usuario.setImc(usuarioService.calcularIMC(usuario));
			usuario.getImc();
			usuarioService.modificarUsuario(usuario);

			if (usuario.getImc() != 0)
				model.addAttribute("mensajeExitoso", usuario.getNombre() +" " + 
			        usuario.getApellidoPat()+"\n tu IMC es: " + String.format("%.2f", usuario.getImc()) );
		} catch (AplicacionExcepcion e) {
			log.error(e.getMessage());
			model.addAttribute("mensajeError", e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "home::#modalMensaje";
	}

}
