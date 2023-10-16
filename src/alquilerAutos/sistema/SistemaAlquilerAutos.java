package alquilerAutos.sistema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import alquilerAutos.modelo.Administrador;
import alquilerAutos.modelo.Categoria;
import alquilerAutos.modelo.DatosCliente;
import alquilerAutos.modelo.InfoCliente;
import alquilerAutos.modelo.InfoEmpleado;
import alquilerAutos.modelo.Inventario;
import alquilerAutos.modelo.Sede;
import alquilerAutos.modelo.Vehiculo;


public class SistemaAlquilerAutos {
	
	private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
	private Inventario inventario;
	private Reserva reserva;
	
	
	public void nuevoCliente() {
		InfoCliente infoCliente = new InfoCliente();
		ArrayList<DatosCliente> clientes = infoCliente.getClientes();
	}
	
	public void cargarInformacionVehiculos()  throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("./inventario/vehiculos.txt"));
		String linea = br.readLine(); 
			
		while (linea != null) 
		{
				
			String[] partes = linea.split(";");
			String placa = partes[0];
			String marca = partes[1];
			String tamaño = partes[2];
			String modelo = partes[3];
			String color = partes[4];
			String caja = partes[5];
			int precioPorDia = Integer.parseInt(partes[6]);
			int maletas = Integer.parseInt(partes[7]);
			int capacidad = Integer.parseInt(partes[8]);
			boolean disponible;
			String disponibilidadDoc = partes[9];
			if(disponibilidadDoc == "true") {
				disponible = true;
			}else{
				disponible = false;
			}
			String categoria = partes[10];
			String sede = partes[11];
				
			Vehiculo elvehiculo = new Vehiculo(placa,marca,tamaño,modelo,color,caja,precioPorDia,maletas,capacidad,disponible, categoria, sede);
			this.vehiculos.add(elvehiculo);
			linea = br.readLine(); 
		}
		
		System.out.println(this.vehiculos);
			
		br.close();
		
}
	
	public void registrarNuevoVehiculo() {
		Administrador administrador = new Administrador(); 
		Vehiculo vehiculo = administrador.registrarNuevoVehiculo();
		this.vehiculos.add(vehiculo);
		System.out.println(this.vehiculos);
	}
	
	
	public Reserva crearReserva() {
	
		Scanner scanner = new Scanner(System.in); 
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("\t\t\tCrear Reserva\n");
		System.out.println("\nIngrese la categoria: ");
		String categoriaVehiculo = scanner.nextLine();
		System.out.println("\nIngrese sede para recoger: ");
		String sedeRecogerVehiculo = scanner.nextLine();
		System.out.println("\nIngrese sede para entragar: ");
		String sedeEntregarrVehiculo = scanner.nextLine();
		System.out.println("\nIngrese fecha-hora para recoger (DD-MM-AA-HH): ");
		String fechaHoraRecogerVehiculo = scanner.nextLine();
		System.out.println("\nIngrese un rango de hora para recoger (N-N): ");
		String rangoHoraRecogerVehiculo = scanner.nextLine();
		System.out.println("\nIngrese fecha para entrega (DD-MM-AA): ");
		String fechaEntregaVehiculo = scanner.nextLine();
		System.out.println("\nIngrese seguro (Opcional): ");
		String seguroVehiculo = scanner.nextLine();
		
		Reserva reserva = new Reserva(categoriaVehiculo, sedeRecogerVehiculo, sedeEntregarrVehiculo, fechaHoraRecogerVehiculo, rangoHoraRecogerVehiculo, fechaEntregaVehiculo, seguroVehiculo);
		return reserva;
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
	
	public int calucularTarifas(Categoria categoria, Vehiculo carro) {
		
		int precioInicial = carro.getPrecioPorDia();
		int excedente = 0;
		int precioFinal = 0;
		String nombreCategoria = categoria.getNombreCategoria();
		
		
		if(nombreCategoria == "economico") {
			excedente += 50000;
			precioFinal = precioInicial + excedente;
		} else if(nombreCategoria == "intermedio") {
			excedente += 70000;
			precioFinal = precioInicial + excedente;	
		} else if(nombreCategoria == "tranportemultiple") {
			excedente += 90000;
			precioFinal = precioInicial + excedente;	
		} else if(nombreCategoria == "vehiculolujo") {
			excedente += 100000;
			precioFinal = precioInicial + excedente;	
		}
		
		return precioFinal;
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

