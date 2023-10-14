package alquilerAutos.sistema;

import java.util.ArrayList;

import alquilerAutos.modelo.Categoria;
import alquilerAutos.modelo.DatosCliente;
import alquilerAutos.modelo.InfoCliente;
import alquilerAutos.modelo.InfoEmpleado;
import alquilerAutos.modelo.Inventario;
import alquilerAutos.modelo.Sede;
import alquilerAutos.modelo.Vehiculo;

public class SistemaAlquilerAutos {
	
	private Inventario inventario;
	private Reserva reserva;
	
	
	public void nuevoCliente() {
		InfoCliente infoCliente = new InfoCliente();
		ArrayList<DatosCliente> clientes = infoCliente.getClientes();
	}
	
	
	public Reserva crearReserva() {
		return null;
	}
	
	public String modificarReserva() {
		return null;
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
}
