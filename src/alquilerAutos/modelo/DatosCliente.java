package alquilerAutos.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import alquilerAutos.sistema.Reserva;

public class DatosCliente {
	private DatosBasicos datosBasicos;
	private DatosLicencia datosLicencia;
	//private Reserva reservaActiva;
	//private String metodoPago;
	public DatosCliente(DatosBasicos datosBasicos, DatosLicencia datosLicencia) {
	
		this.datosBasicos = datosBasicos;
		this.datosLicencia = datosLicencia;
	}
	public DatosBasicos getDatosBasicos() {
		return datosBasicos;
	}
	public DatosLicencia getDatosLicencia() {
		return datosLicencia;
	}
	
}
