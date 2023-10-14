package alquilerAutos.modelo;

import java.io.File;

public class DatosLicencia {
	private String pais;
	private int numero;
	private String fechaVencimiento;
	//private File imagen;
	
	public DatosLicencia(String pais, int numero, String fechaVencimiento) {
		this.pais = pais;
		this.numero = numero;
		this.fechaVencimiento = fechaVencimiento;
		//this.imagen = imagen;
	}

	public int getNumero() {
		return numero;
	}
}
