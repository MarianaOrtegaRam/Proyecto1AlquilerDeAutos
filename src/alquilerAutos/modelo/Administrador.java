package alquilerAutos.modelo;

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
			String modeloVehiculo, String colorVehiculo, String cajaVehiculo, String precioPorDiaVehiculo,
			String maletasVehiculo,
			String capacidadVehiculo, String categoriaVehiculo, String sedeVehiculo) {

		guardarVehiculo(placaVehiculo + ";" + marcaVehiculo.toLowerCase() + ";" + tamañoVehiculo.toLowerCase() + ";"
				+ modeloVehiculo + ";"
				+ colorVehiculo + ";" + cajaVehiculo + ";" + precioPorDiaVehiculo + ";" + maletasVehiculo + ";"
				+ capacidadVehiculo + ";" + "true" + ";" + categoriaVehiculo.toLowerCase() + ";" + sedeVehiculo);
		guardarSede(sedeVehiculo + ";" + placaVehiculo);

		int precioPorDia = Integer.parseInt(precioPorDiaVehiculo);
		int maletas = Integer.parseInt(maletasVehiculo);
		int capacidad = Integer.parseInt(capacidadVehiculo);

		Vehiculo vehiculo = new Vehiculo(placaVehiculo, marcaVehiculo, tamañoVehiculo, modeloVehiculo, colorVehiculo,
				cajaVehiculo, precioPorDia, maletas, capacidad, true, categoriaVehiculo, sedeVehiculo);
		System.out.println("\nSe ha registrado el vehiculo!\n");
		return vehiculo;
	}

	public AdministradorSede registrarAdminSede(String nombre, String cedula, String login, String contraseña, String sede) {
		
		AdministradorSede adminSede = new AdministradorSede(nombre,cedula,login,contraseña,sede);
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
		FileWriter filewriter;

		try {
			filewriter = new FileWriter("./inventario/vehiculos.txt", true);
			filewriter.write("\n" + texto);
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void guardarSede(String texto) {
		FileWriter filewriter;
		try {
			filewriter = new FileWriter("./inventario/sedesvehiculos.txt", true);
			filewriter.write("\n" + texto);
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void guardarSeguro(String texto) {
		FileWriter filewriter;
		try {
			filewriter = new FileWriter("./inventario/seguros.txt", true);
			filewriter.write("\n" + texto.toLowerCase());
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void guardarAdminSede(String texto) {
		FileWriter filewriter;
		try {
			filewriter = new FileWriter("./inventario/adminsedes.txt", true);
			filewriter.write("\n" + texto);
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
