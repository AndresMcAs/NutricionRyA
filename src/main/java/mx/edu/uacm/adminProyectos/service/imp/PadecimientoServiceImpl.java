package mx.edu.uacm.adminProyectos.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.adminProyectos.dominio.Padecimiento;
import mx.edu.uacm.adminProyectos.repository.PadecimientoRepository;
import mx.edu.uacm.adminProyectos.service.PadecimientoService;

@Service
@Slf4j
public class PadecimientoServiceImpl implements PadecimientoService {
	
	@Autowired
	private PadecimientoRepository padecimientoRepository;
	
	
	@Override
	public Padecimiento obtenerPadecimientoEstado(String estado) {
		
		if(log.isDebugEnabled())
			log.debug("> entrando a EjercicioService.obtenerEjercicio");
		 Padecimiento padecimientoEstado =padecimientoRepository.findByEstado(estado);
		if (padecimientoEstado != null)
		 return padecimientoEstado;
		else 
			return null;
	}
  
		

}
