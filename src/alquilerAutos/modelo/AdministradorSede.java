package alquilerAutos.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdministradorSede {
	private String nombre;
	private int cedula;
	private String login;
	private String contraseña;

	public AdministradorSede(String nombre, int cedula, String login, String contraseña) {

		this.nombre = nombre;
		this.cedula = cedula;
		this.login = login;
		this.contraseña = contraseña;
	}
	
	public void cambiarSede(String placa, String sede, Map<String, ArrayList<String>> sedes){
		
		for (java.util.Map.Entry<String, ArrayList<String>> entry : sedes.entrySet()) {
	            String nombre = entry.getKey();
	            ArrayList<String> placas = entry.getValue();

	            if (placas.contains(placa)) {
	                System.out.println("Se encontró la placa " + placa + " asociada a " + nombre);
	                placas.remove(placa);
	            }
	            
	            if (nombre.equals(sede)) {
	            	placas.add(placa);
	            	System.out.println("Se añadió la placa " + placa + " a la sede " + sede);
	            }
	            
	           
	        }
		System.out.println(sedes);
	}
	
	public void eliminarVehiculo(){
		
	}

}
