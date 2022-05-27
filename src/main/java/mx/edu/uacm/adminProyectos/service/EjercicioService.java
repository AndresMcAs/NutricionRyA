package mx.edu.uacm.adminProyectos.service;

import java.util.List;

import mx.edu.uacm.adminProyectos.dominio.Ejercicio;

public interface EjercicioService {
	  
	
	 Ejercicio obtenerEjercicioEstado(String estado);
	 List<Ejercicio> obtenerEjercicios();
	 
}