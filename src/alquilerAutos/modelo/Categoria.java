package alquilerAutos.modelo;

import java.util.ArrayList;

public class Categoria {
	
	private String nombreCategoria;
	private ArrayList<String> condicionesBasicas; 
	
	
	public Categoria (String nombreCategoria, ArrayList<String> condicionesBasicas) {
		this.nombreCategoria = nombreCategoria;
		this.condicionesBasicas = condicionesBasicas;
		
	
	}
	
	public String agregarNuevoVehiculo() {
		return null;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}
	
	public ArrayList<Vehiculo> getVehiculosDisponibles() {
		return null;
	}
}
