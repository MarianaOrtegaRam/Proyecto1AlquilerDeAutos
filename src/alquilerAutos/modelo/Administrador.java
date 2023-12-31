package alquilerAutos.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrador {
	public static String nombre = "Andres";
	public static int cedula = 1001456897;
	public static String login = "andres7";
	public static String contraseña = "veronica62";

	public DatosBasicos registrarEmpleado(String nombreEmpleado, String datoContactoEmpleado,
			String fechaNacimientoEmpleado,
			String nacionalidadEmplado, String loginEmpleado, String contraseñaEmpleado) {

		InfoEmpleado infoEmpleado = new InfoEmpleado();

		DatosBasicos datosBasicoEmpleado = infoEmpleado.crearEmpleado(nombreEmpleado, datoContactoEmpleado,
				fechaNacimientoEmpleado, nacionalidadEmplado, loginEmpleado, contraseñaEmpleado);

		return datosBasicoEmpleado;
	}

	public Vehiculo registrarNuevoVehiculo(String placaVehiculo, String marcaVehiculo, String tamañoVehiculo,
			String modeloVehiculo, String colorVehiculo, String precioPorDiaVehiculo,
			String maletasVehiculo,
			String capacidadVehiculo, String categoriaVehiculo, String sedeVehiculo, String tipoVehiculo) {

		if (tipoVehiculo.equals("automovil")) {
			guardarVehiculo(placaVehiculo + ";" + marcaVehiculo.toLowerCase() + ";" + tamañoVehiculo.toLowerCase() + ";"
					+ modeloVehiculo + ";"
					+ colorVehiculo + ";" + precioPorDiaVehiculo + ";" + maletasVehiculo + ";"
					+ capacidadVehiculo + ";" + "true" + ";" + categoriaVehiculo.toLowerCase() + ";" + sedeVehiculo
					+ ";"
					+ tipoVehiculo);
			guardarSede(sedeVehiculo + ";" + placaVehiculo);
		} else {
			guardarOtros(placaVehiculo + ";" + marcaVehiculo.toLowerCase() + ";" + tamañoVehiculo.toLowerCase() + ";"
					+ modeloVehiculo + ";"
					+ colorVehiculo + ";" + precioPorDiaVehiculo + ";" + maletasVehiculo + ";"
					+ capacidadVehiculo + ";" + "true" + ";" + categoriaVehiculo.toLowerCase() + ";" + sedeVehiculo
					+ ";"
					+ tipoVehiculo, tipoVehiculo);
			guardarSedeOtro(sedeVehiculo + ";" + placaVehiculo, tipoVehiculo);

		}

		int precioPorDia = Integer.parseInt(precioPorDiaVehiculo);
		int maletas = Integer.parseInt(maletasVehiculo);
		int capacidad = Integer.parseInt(capacidadVehiculo);

		Vehiculo vehiculo = new Vehiculo(placaVehiculo, marcaVehiculo, tamañoVehiculo, modeloVehiculo, colorVehiculo,
				precioPorDia, maletas, capacidad, true, categoriaVehiculo, sedeVehiculo, tipoVehiculo);
		System.out.println("\nSe ha registrado el vehiculo!\n");
		return vehiculo;
	}

	public AdministradorSede registrarAdminSede(String nombre, String cedula, String login, String contraseña,
			String sede) {

		AdministradorSede adminSede = new AdministradorSede(nombre, cedula, login, contraseña, sede);
		guardarAdminSede(nombre + ";" + cedula + ";" + login + ";" + contraseña + ";" + sede);
		return adminSede;
	}

	public Seguro configurarSeguro(String nombreSeguro, String precioSeguro, String beneficiosVehiculo) {

		guardarSeguro(nombreSeguro + ";" + precioSeguro + ";" + beneficiosVehiculo);

		Seguro seguro = new Seguro(nombreSeguro, Integer.parseInt(precioSeguro), beneficiosVehiculo);
		return seguro;
	}

	public static String getLogin() {
		return login;
	}

	public static String getContraseña() {
		return contraseña;
	}

	public void guardarVehiculo(String texto) {
	    StringBuilder contenidoAntiguo = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader("./inventario/vehiculos.txt"))) {
	        String linea = br.readLine();
	        while (linea != null) {
	            contenidoAntiguo.append(linea).append("\n");
	            linea = br.readLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    String contenidoNuevo = contenidoAntiguo.toString() + texto;

	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("./inventario/vehiculos.txt"))) {
	        bw.write(contenidoNuevo);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void guardarSede(String texto) {
		 StringBuilder contenidoAntiguo = new StringBuilder();
		    try (BufferedReader br = new BufferedReader(new FileReader("./inventario/sedesvehiculos.txt"))) {
		        String linea = br.readLine();
		        while (linea != null) {
		            contenidoAntiguo.append(linea).append("\n");
		            linea = br.readLine();
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    String contenidoNuevo = contenidoAntiguo.toString() + texto;

		    try (BufferedWriter bw = new BufferedWriter(new FileWriter("./inventario/sedesvehiculos.txt"))) {
		        bw.write(contenidoNuevo);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}

	public void guardarOtros(String texto, String tipo) {
		StringBuilder contenidoAntiguo = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader("./inventario/" + tipo + ".txt"))) {
	        String linea = br.readLine();
	        while (linea != null) {
	            contenidoAntiguo.append(linea).append("\n");
	            linea = br.readLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    String contenidoNuevo = contenidoAntiguo.toString() + texto;

	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("./inventario/" + tipo + ".txt"))) {
	        bw.write(contenidoNuevo);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void guardarSedeOtro(String texto, String tipo) {
		StringBuilder contenidoAntiguo = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader("./inventario/sedes" + tipo + ".txt"))) {
	        String linea = br.readLine();
	        while (linea != null) {
	            contenidoAntiguo.append(linea).append("\n");
	            linea = br.readLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    String contenidoNuevo = contenidoAntiguo.toString() + texto;

	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("./inventario/sedes" + tipo + ".txt"))) {
	        bw.write(contenidoNuevo);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void guardarSeguro(String texto) {
		StringBuilder contenidoAntiguo = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader("./inventario/seguros.txt"))) {
	        String linea = br.readLine();
	        while (linea != null) {
	            contenidoAntiguo.append(linea).append("\n");
	            linea = br.readLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    String contenidoNuevo = contenidoAntiguo.toString() + texto;

	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("./inventario/seguros.txt"))) {
	        bw.write(contenidoNuevo);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void guardarAdminSede(String texto) {
		StringBuilder contenidoAntiguo = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader("./inventario/adminsedes.txt"))) {
	        String linea = br.readLine();
	        while (linea != null) {
	            contenidoAntiguo.append(linea).append("\n");
	            linea = br.readLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    String contenidoNuevo = contenidoAntiguo.toString() + texto;

	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("./inventario/adminsedes.txt"))) {
	        bw.write(contenidoNuevo);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
