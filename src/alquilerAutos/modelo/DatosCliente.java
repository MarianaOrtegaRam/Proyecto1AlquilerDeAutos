package alquilerAutos.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import alquilerAutos.sistema.Reserva;

public class DatosCliente {
	private DatosBasicos datosBasicos;
	private DatosLicencia datosLicencia;
	private Reserva reservaActiva;
	private MetodoDePago metodoDePago;

	public DatosCliente(DatosBasicos datosBasicos, DatosLicencia datosLicencia, String claseMetodoPago,
			int numTarjeta, String fechaVencimiento, int codigoSeguridad, String password) {

		this.datosBasicos = datosBasicos;
		this.datosLicencia = datosLicencia;
		try {
			Class clase = Class.forName("alquilerAutos.modelo." + claseMetodoPago);

			this.metodoDePago = (MetodoDePago) clase
					.getDeclaredConstructor(int.class, String.class, int.class, String.class)
					.newInstance(numTarjeta, fechaVencimiento, codigoSeguridad, password);
		} catch (ClassNotFoundException e) {
			System.out.println("No existe la clase " + claseMetodoPago);
		} catch (Exception e) {
			System.out.println("Hubo otro error construyendo el metodo de Pago: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public DatosBasicos getDatosBasicos() {
		return datosBasicos;
	}

	public DatosLicencia getDatosLicencia() {
		return datosLicencia;
	}

	public void setReservaActiva(Reserva reservaActiva) {
		this.reservaActiva = reservaActiva;
	}

}
