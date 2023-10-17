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
	
	
	public DatosBasicos registrarEmpleado() {
		InfoEmpleado infoEmpleado = new InfoEmpleado();
		DatosBasicos datosBasicoEmpleado = infoEmpleado.crearEmpleado();
		return datosBasicoEmpleado;
	}
	
	public Vehiculo registrarNuevoVehiculo() {
			Scanner scanner = new Scanner(System.in); 
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("\t\t\tDatos Vehiculo\n");
			System.out.println("Ingrese placa: ");
			String placaVehiculo = scanner.nextLine();
			System.out.println("\nIngrese marca: ");
			String marcaVehiculo = scanner.nextLine();
			System.out.println("\nIngrese tamaño: ");
			String tamañoVehiculo = scanner.nextLine();
			System.out.println("\nIngrese modelo: ");
			String modeloVehiculo = scanner.nextLine();
			System.out.println("\nIngrese color: ");
			String colorVehiculo = scanner.nextLine();
			System.out.println("\nIngrese caja: ");
			String cajaVehiculo = scanner.nextLine();
			System.out.println("\nIngrese precio por dia: ");
			String precioPorDiaVehiculo = scanner.nextLine();
			System.out.println("\nIngrese maletas: ");
			String maletasVehiculo = scanner.nextLine();
			System.out.println("\nIngrese capacidad: ");
			String capacidadVehiculo = scanner.nextLine();
			System.out.println("\nIngrese categoria: ");
			String categoriaVehiculo = scanner.nextLine();
			System.out.println("\nIngrese sede: ");
			String sedeVehiculo = scanner.nextLine();
			
			guardarVehiculo(placaVehiculo+";"+marcaVehiculo+";"+tamañoVehiculo+";"+modeloVehiculo+";"+colorVehiculo+";"+cajaVehiculo+";"+precioPorDiaVehiculo+";"+maletasVehiculo+";"
							+capacidadVehiculo+";"+"true"+";"+categoriaVehiculo+";"+sedeVehiculo);
			
			int precioPorDia = Integer.parseInt(precioPorDiaVehiculo);
			int maletas = Integer.parseInt(maletasVehiculo);
			int capacidad = Integer.parseInt(capacidadVehiculo);
		
			
			Vehiculo vehiculo = new Vehiculo(placaVehiculo,marcaVehiculo,tamañoVehiculo,modeloVehiculo,colorVehiculo,cajaVehiculo
											,precioPorDia,maletas,capacidad,true,categoriaVehiculo,sedeVehiculo);
			System.out.println("\nSe ha registrado el vehiculo!\n");
			return vehiculo;
	}

	
	public void darVehiculoDeBaja(String placaVehiculo){
		
	}
	
	public Seguro configurarSeguro(){
		Scanner scanner = new Scanner(System.in); 
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("\t\t\tConfiguracion nuevo seguro\n");
		System.out.println("Ingrese nombre: ");
		String nombreSeguro = scanner.nextLine();
		System.out.println("\nIngrese precio: ");
		String precioSeguro = scanner.nextLine();
		System.out.println("\nIngrese beneficios: ");
		String beneficiosVehiculo = scanner.nextLine();
		
		guardarSeguro(nombreSeguro+";"+precioSeguro+";"+beneficiosVehiculo);
		
		Seguro seguro = new Seguro(nombreSeguro,Integer.parseInt(precioSeguro),beneficiosVehiculo);
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
			filewriter = new FileWriter("./inventario/vehiculos.txt",true);
			filewriter.write("\n"+texto.toLowerCase());
			filewriter.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarSeguro(String texto) {
		FileWriter filewriter;
		try {
			filewriter = new FileWriter("./inventario/seguros.txt",true);
			filewriter.write("\n"+texto.toLowerCase());
			filewriter.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	
