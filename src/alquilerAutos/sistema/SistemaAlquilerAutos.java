package alquilerAutos.sistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import alquilerAutos.modelo.Categoria;
import alquilerAutos.modelo.DatosCliente;
import alquilerAutos.modelo.InfoCliente;
import alquilerAutos.modelo.InfoEmpleado;
import alquilerAutos.modelo.Inventario;
import alquilerAutos.modelo.Sede;
import alquilerAutos.modelo.Vehiculo;

public class SistemaAlquilerAutos {
	
	private ArrayList<Vehiculo> vehiculos;
	private Inventario inventario;
	private Reserva reserva;
	
	
	public void nuevoCliente() {
		InfoCliente infoCliente = new InfoCliente();
		ArrayList<DatosCliente> clientes = infoCliente.getClientes();
	}
	
	
	public Reserva crearReserva() {
	
		Scanner scanner = new Scanner(System.in); 
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("\t\t\tCrear Reserva\n");
		System.out.println("\nIngrese la categoria: ");
		String categoriaVehiculo = scanner.nextLine();
		System.out.println("\nIngrese sede para recoger: ");
		String sedeRecogerVehiculo = scanner.nextLine();
		System.out.println("\nIngrese fecha-hora para recoger (DD-MM-AA-HH): ");
		String fechaHoraRecogerVehiculo = scanner.nextLine();
		System.out.println("\nIngrese un rango de hora para recoger (N-N): ");
		String rangoHoraRecogerVehiculo = scanner.nextLine();
		System.out.println("\nIngrese un login para registrarse: ");
		String loginCliente = scanner.nextLine();
		System.out.println("\nIngrese contraseña: ");
		String contraseñaCliente = scanner.nextLine();
		return null;
	}
	
	public void modificarReserva() {

		Scanner scanner = new Scanner(System.in); 
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("\t\t\tModificar Reserva\n");
		System.out.println("\n1. Modificar la sede de entrega");
		System.out.println("2. Modificar Rango de hora de entrega");
		System.out.println("3. Modicar fecha entrega");
		
		int opcionSeleccionada = Integer.parseInt(input("\nPor favor seleccione una opcion"));
		
		if (opcionSeleccionada == 1) {
			String sedeEntrega = scanner.nextLine();
			this.reserva.setSedeEntrega(sedeEntrega);
			
		}
		
		if (opcionSeleccionada == 2) {
			String rangoEntrega = scanner.nextLine();
			this.reserva.setRangoHoraEntrega(rangoEntrega);
		}
		
		if (opcionSeleccionada == 3) {
			String fechaEntrega = scanner.nextLine();
			this.reserva.setFechaEntrega(fechaEntrega);
		}
	}
	
	public float calucularaTarifas(Categoria categoria, Vehiculo carro) {
		return 0;
	}
	
	public Sede buscarVehiculo(Vehiculo auto){
		return null;
	}
	
	public boolean disponibilidadVehiculo(Vehiculo auto){
		return false;
	}
	
	public Vehiculo seleccionarAutoReserva(Categoria categoriaSeleccionada){
		return null;
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

}

