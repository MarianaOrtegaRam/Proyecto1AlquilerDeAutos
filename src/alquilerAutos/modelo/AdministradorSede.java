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
	private String cedula;
	private String login;
	private String contraseña;
	private String sede;

	public AdministradorSede(String nombre, String cedula, String login, String contraseña, String sede) {

		this.nombre = nombre;
		this.cedula = cedula;
		this.login = login;
		this.contraseña = contraseña;
		this.sede = sede;
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
	
	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public void eliminarVehiculo(){
		
	}

}
