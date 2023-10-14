package alquilerAutos.modelo;

import java.util.ArrayList;

public class Administrador {
	public static String nombre = "Andres";
	public static int cedula = 1001456897;
	public static String login = "andres7";
	public static String contraseña = "veronica62";
	
	
	public void registrarEmpleado() {
		InfoEmpleado infoEmpleado = new InfoEmpleado();
		infoEmpleado.crearEmpleado();
	}
	
	public void registrarNuevoVehiculo() {
		
	}
	
	public void darVehiculoDeBaja(String placaVehiculo){
		
	}
	
	public void configurarSeguro(String nombre, ArrayList<String> beneficios, float precio){
		
	}

	public static String getLogin() {
		return login;
	}

	public static String getContraseña() {
		return contraseña;
	}

}
