package alquilerAutos.sistema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import alquilerAutos.modelo.Administrador;
import alquilerAutos.modelo.Categoria;
import alquilerAutos.modelo.DatosBasicos;
import alquilerAutos.modelo.DatosCliente;
import alquilerAutos.modelo.DatosLicencia;
import alquilerAutos.modelo.InfoCliente;
import alquilerAutos.modelo.InfoEmpleado;
import alquilerAutos.modelo.Inventario;
import alquilerAutos.modelo.Sede;
import alquilerAutos.modelo.Vehiculo;


public class SistemaAlquilerAutos {
	
	private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
	private ArrayList<DatosCliente> clientes = new ArrayList<>();
	private Map<String,ArrayList<String>> sedes = new HashMap<>();
	private Map<String,String[]> condicionesCategoria = new HashMap<>();
	private Inventario inventario;
	private Reserva reserva;
	
	
	public void nuevoCliente() {
		InfoCliente infoCliente = new InfoCliente();
		DatosCliente cliente = infoCliente.crearCliente();
    	this.clientes.add(cliente);
    	System.out.println(clientes);
	}
	
	public void cargarInformacionCliente()  throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("./inventario/clientes.txt"));
		String linea = br.readLine(); 
		
		while (linea != null) 
		{
				
			String[] partes = linea.split(";");
			String nombre = partes[0];
			String datoContacto = partes[1];
			String fechaNacimiento = partes[2];
			String nacionalidad = partes[3];
			String login = partes[4];
			String contraseña = partes[5];
			String pais = partes[6];
			String numero = partes[7];
			String fechaVencimiento = partes[8];
				
			DatosBasicos datosBasicos = new DatosBasicos(nombre,datoContacto,fechaNacimiento,nacionalidad,login,contraseña,"cliente");
			DatosLicencia datosLicencia = new DatosLicencia(pais,Integer.parseInt(numero),fechaVencimiento);
			
			DatosCliente datosCliente = new DatosCliente(datosBasicos,datosLicencia);
			this.clientes.add(datosCliente);
			linea = br.readLine(); 
		}
			
		br.close();
	}
	
	public boolean verificarCliente(String login, String contraseña){
		  
		boolean verificacion = false;
		
	    for (int i = 0; i < clientes.size(); i++){ 
		String loginUnCliente = clientes.get(i).getDatosBasicos().getLogin();
		String contraseñaUnCliente = clientes.get(i).getDatosBasicos().getContraseña();
		
		if(loginUnCliente.equals(login) && contraseñaUnCliente.equals(contraseña)) {
			verificacion = true;
			
			} 
		}
	    return verificacion;
	  
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
			
		br.close();
		
}
	
	public void cargarInformacionCondicionesCategoria()  throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("./inventario/sedesvehiculos.txt"));
		String linea = br.readLine(); 
			
		while (linea != null) 
		{
				
			String [] partes  = linea.split(";");
			String nombreCategoria = partes[0];
			String condiciones = partes[1];
			String[] condiciones1 = condiciones.split("!");
			
			String[] condicionesCate = condicionesCategoria.get(nombreCategoria);
			if (condicionesCate == null){
				condicionesCategoria.put(nombreCategoria, condiciones1);
			}
				
			linea = br.readLine(); 
		}
			
		br.close();
		
}
	public void cargarInformacionSedes()  throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("./inventario/sedesvehiculos.txt"));
		String linea = br.readLine(); 
			
		while (linea != null) 
		{
				
			String [] partes  = linea.split(";");
			String nombreSede = partes[0];
			String placa = partes[1];
			ArrayList<String> sedeExiste = sedes.get(nombreSede);
			if (sedeExiste == null){
				ArrayList<String> carros = new ArrayList<>();
				carros.add(placa);
				sedes.put(nombreSede,carros);
			}
			else {
				ArrayList<String> lista = sedes.get(nombreSede);
				lista.add(placa);
			}
				
			linea = br.readLine(); 
		}
			
		br.close();
		
}
	public void registrarNuevoVehiculo() {
		Administrador administrador = new Administrador(); 
		Vehiculo vehiculo = administrador.registrarNuevoVehiculo();
		this.vehiculos.add(vehiculo);
		String sede = vehiculo.getsede();
		
		ArrayList<String> sedeExiste = sedes.get(sede);
		if (sedeExiste == null){
			ArrayList<String> carros = new ArrayList<>();
			carros.add(vehiculo.getPlaca());
			sedes.put(sede,carros);
		}
		else {
			ArrayList<String> lista = sedes.get(sede);
			lista.add(vehiculo.getPlaca());
		}
		
	}
	
	
	public void crearReserva() {
	
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
		
		ArrayList<String> listaPlacas = sedes.get(sedeRecogerVehiculo);
		
		Vehiculo candidato = null;
		Vehiculo categoriaDeseada = null;
		
		 for (int i = 0; i < listaPlacas.size(); i++) {
	            String unaPlaca = listaPlacas.get(i);   
	            for(int j = 0; j < this.vehiculos.size(); j++) {
	            	Vehiculo unVehiculo = vehiculos.get(j);
	            	if(unaPlaca == unVehiculo.getPlaca()) {
	            		String categoriaUnVehiculo = unVehiculo.getCategoria();
	            		if(categoriaUnVehiculo == categoriaVehiculo && unVehiculo.getDisponibilidad()){
	            			categoriaDeseada = unVehiculo;
	            		}else if(categoriaUnVehiculo != categoriaVehiculo && unVehiculo.getDisponibilidad()) {
	            			String[] condicionesUnaCategoria = condicionesCategoria.get(categoriaVehiculo);
	            			boolean cumple = unVehiculo.verificarCondiciones(condicionesUnaCategoria);
	            			if(cumple) {
	            				candidato = unVehiculo;
	            			}
	            					
	            		}
	            	}
	            }
	        }
		 
		 if(categoriaDeseada != null) {
			 reserva.setVehiculo(categoriaDeseada);
			 categoriaDeseada.setDisponible(false);
			 System.out.println("Reserva exitosa!");
		 }else if(candidato != null) {
			 reserva.setVehiculo(candidato);
			 System.out.println("No se encontro disponible la categoria deseada, sin embargo, este vehiculo cumple sus nececidades");
			 candidato.setDisponible(false);
		 }else{
			 reserva = null;
			 System.out.println("No se pudo realizar la reserva");
		 }
		 
		 
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
	
	public String buscarVehiculo(Vehiculo auto){
		String placa = auto.getPlaca();
		String sede = "No se encuentra!";
		
		 for (int i = 0; i < vehiculos.size(); i++) {
	            Vehiculo prueba = vehiculos.get(i);
	            if(placa == prueba.getPlaca()) {
	            	 sede = auto.getsede();
	            }
	        }
		 
		 return sede;
	
		
	}
	
	public String disponibilidadVehiculo(Vehiculo auto){
		
		String placa = auto.getPlaca();
		String disponibilidad = "No se encuentra disponible!";
		
		 for (int i = 0; i < vehiculos.size(); i++) {
	            Vehiculo prueba = vehiculos.get(i);
	            if(placa == prueba.getPlaca() && prueba.getDisponibilidad()){
	            	 disponibilidad = "Se encuentra disponible";
	            }
	        }
		 
		 return disponibilidad;
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

