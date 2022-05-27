package mx.edu.uacm.adminProyectos.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.dominio.Comida;
import mx.edu.uacm.adminProyectos.service.ComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * controlador para la gestion de cursos 
 * @author Andres Mendoza 
 *
 */
@Controller
@RequestMapping("/comida")
@Slf4j
public class ComidaController {
	
  @Autowired
  private ComidaService comidaService;
    
  /**
   *lista toda los alimentos 
   * usuario consuma
   * @param model
   * @return
   */
  @GetMapping("/listarcomida")
  public String buscar(Model model) {
	if (log.isDebugEnabled())
      log.debug("> Entrando a buscarComida <");
    List<Comida> comida = comidaService.listAll();
    model.addAttribute("listaComida", comida);
    return "alimentos";
  }
  
  /**
   * Busca camida por nombre
   * @param model
   * @param comida
   * @param nombre
   * @return
   */
  @GetMapping("/buscacomida")
	public String busquedaComida(Model model,Comida comida , String nombre){
		if (log.isDebugEnabled()) {
			log.debug(">Entrando a usuarioController.comida");
			log.debug("Comida {}", comida);
		}

			comida = comidaService.obtenerComidaNombre(nombre);
			if (comida!=null)
				model.addAttribute("mensajeExitoso",  "Porcion:"+ comida.getPorcion()+" de "+ comida.getNombre() +" contine " + comida.getCalorias() );
			
			else 
			model.addAttribute("mensajeError", "Alimento No encontrado");
		
		return "alimentos::#modalMensaje";
	}
  
	/**
	 * muestra la tabla de alimentos paginados
	 * @param model
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/comidapaginada")
	public String buscarPaginado(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional <Integer> size) {
		
		if (log.isDebugEnabled()) {
			
			log.debug("entrando a comidaPaginada");
		}
		//numero de pagina
		final int paginaActual = page.orElse(3);
		final int tamañoPAgina = size.orElse(20);
		
		Page <Comida> comidaPaginada = comidaService.obtenerPlatillosPaginados(PageRequest.of(paginaActual-1, tamañoPAgina));
		model.addAttribute("listaComida", comidaPaginada);
		int totalPagina = comidaPaginada.getTotalPages();
		if(totalPagina>0) {
			
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPagina).boxed().collect(Collectors.toList());
			model.addAttribute("PageNumbers", pageNumbers);
		}
		
		return "comida1::#resultado";
	}
  
	
}
