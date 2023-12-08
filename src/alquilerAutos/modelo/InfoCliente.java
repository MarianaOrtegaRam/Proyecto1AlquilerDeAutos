package alquilerAutos.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InfoCliente {

	public DatosCliente crearCliente(String nombreCliente, String datoContactoCliente, String fechaNacimientoCliente,
			String nacionalidadCliente, String loginCliente, String contraseñaCliente, String paisLicenciaCliente,
			String numeroLicenciaCliente,
			String fechaVencimientoLicenciaCliente, String metodoDePago, int numTarjeta,
			String fechaVencimiento,
			int codigoSeguridad, String password) {

		DatosBasicos datosBasicos = datosBasicosCliente(nombreCliente, datoContactoCliente, fechaNacimientoCliente,
				nacionalidadCliente, loginCliente, contraseñaCliente);

		DatosLicencia datosLicencia = datoslicenciaCliente(paisLicenciaCliente, numeroLicenciaCliente,
				fechaVencimientoLicenciaCliente);

		agregarClienteMetodoPago(metodoDePago + ";" + String.valueOf(numTarjeta) + ";" + fechaVencimiento + ";"
				+ String.valueOf(codigoSeguridad) + ";" + password);
		DatosCliente datosCliente = new DatosCliente(datosBasicos, datosLicencia, metodoDePago, numTarjeta,
				fechaVencimiento,
				codigoSeguridad, password);
		return datosCliente;
	}

	public DatosBasicos datosBasicosCliente(String nombreCliente, String datoContactoCliente,
			String fechaNacimientoCliente, String nacionalidadCliente,
			String loginCliente, String contraseñaCliente) {

		agregarClienteBasicos(nombreCliente + ";" + datoContactoCliente + ";" + fechaNacimientoCliente + ";"
				+ nacionalidadCliente + ";" + loginCliente + ";" + contraseñaCliente);

		DatosBasicos datosBasicosCliente = new DatosBasicos(nombreCliente, datoContactoCliente, fechaNacimientoCliente,
				nacionalidadCliente, loginCliente, contraseñaCliente, "cliente");

		return datosBasicosCliente;

	}

	public DatosLicencia datoslicenciaCliente(String paisLicenciaCliente, String numeroLicenciaCliente,
			String fechaVencimientoLicenciaCliente) {

		agregarClienteLicencias(
				paisLicenciaCliente + ";" + numeroLicenciaCliente + ";" + fechaVencimientoLicenciaCliente);

		DatosLicencia datosLicenciaCliente = new DatosLicencia(paisLicenciaCliente,
				Integer.parseInt(numeroLicenciaCliente), fechaVencimientoLicenciaCliente);

		return datosLicenciaCliente;

	}

	public void agregarClienteBasicos(String texto) {
		StringBuilder contenidoAntiguo = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader("./inventario/clientes.txt"))) {
		    String linea = br.readLine();
		    while (linea != null) {
		        contenidoAntiguo.append(linea).append("\n");
		        linea = br.readLine();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}

		String contenidoNuevo = contenidoAntiguo.toString() + texto.toLowerCase() + ";";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("./inventario/clientes.txt"))) {
		    bw.write(contenidoNuevo);
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}

	public void agregarClienteLicencias(String texto) {
		StringBuilder contenidoAntiguo = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader("./inventario/clientes.txt"))) {
		    String linea = br.readLine();
		    while (linea != null) {
		        contenidoAntiguo.append(linea).append("\n");
		        linea = br.readLine();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}

		String contenidoNuevo = contenidoAntiguo.toString() + texto.toLowerCase() + ";";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("./inventario/clientes.txt"))) {
		    bw.write(contenidoNuevo);
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}

	public void agregarClienteMetodoPago(String texto) {
		StringBuilder contenidoAntiguo = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader("./inventario/clientes.txt"))) {
		    String linea = br.readLine();
		    while (linea != null) {
		        contenidoAntiguo.append(linea).append("\n");
		        linea = br.readLine();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}

		String contenidoNuevo = contenidoAntiguo.toString() + texto;

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("./inventario/clientes.txt"))) {
		    bw.write(contenidoNuevo);
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}
}

