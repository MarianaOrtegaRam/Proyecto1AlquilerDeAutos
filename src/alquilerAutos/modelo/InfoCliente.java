package alquilerAutos.modelo;

import java.util.ArrayList;

public class InfoCliente {
	
	private ArrayList<DatosCliente> clientes;
	
	DatosCliente datos = new DatosCliente();
	
	public void crearCliente() {
	clientes.add(datos);
	}
	
	public ArrayList<DatosCliente> getClientes() {
		return clientes;
	}

}

